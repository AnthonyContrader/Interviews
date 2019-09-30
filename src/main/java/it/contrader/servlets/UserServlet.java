package it.contrader.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CompanyNameDTO;
import it.contrader.dto.UserDTO;
import it.contrader.dto.UserTypeDistinctDTO;
import it.contrader.service.UserService;
import it.contrader.service.CompanyService;

/*
 * Per dettagli vedi Guida sez Servlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		UserService service = new UserService();
		request.setAttribute("list", service.getAll());
	}
	
	public void updateCompanyAllList(HttpServletRequest request) {
		CompanyService service = new CompanyService();
		request.setAttribute("companyAllList", service.getCompanyAll());
	}
	
	public void updateUsertypeList(HttpServletRequest request) {
		UserService service = new UserService();
		request.setAttribute("usertypeDistinctAllList", service.getUsertypeDistinctAll());
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		CompanyService companyService = new CompanyService();
		String mode = request.getParameter("mode");
		UserDTO dto;
		int id;
		String username, password, usertype, companyidString;
		boolean ans;
		
		if(request.getSession().getAttribute("user")==null) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
			return;
		}
		if(!((UserDTO)request.getSession().getAttribute("user")).getUsertype().equals("ADMIN")){
			response.sendError(403,"NON AUTORIZZATO");
			return;
		}
		
		switch (mode.toUpperCase()) {

		case "USERLIST":
			updateList(request);
			updateCompanyAllList(request);
			updateUsertypeList(request);
			request.setAttribute("companyAllList", companyService.getCompanyAll());
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;

		case "SEARCH":
			List<UserDTO> userListDTO = new ArrayList<> ();
			List<UserTypeDistinctDTO> usertypeDistinctAllList = userService.getUsertypeDistinctAll();
			List<CompanyNameDTO> companyAllList = companyService.getCompanyAll();
			if (request.getParameter("search") != null) {
				username = "%" + request.getParameter("username") + "%";
				password = "%" + request.getParameter("password") + "%";
				usertype = "%" + request.getParameter("usertype") + "%";
				companyidString = request.getParameter("companyid");
				userListDTO = userService.search(username, password, usertype, companyidString);			
				updateCompanyAllList(request);
			}
				request.setAttribute("userResultList", userListDTO);
				request.setAttribute("usertypeDistinctAllList", usertypeDistinctAllList);
				request.setAttribute("companyAllList", companyAllList);
				getServletContext().getRequestDispatcher("/user/searchuser.jsp").forward(request,  response);
			break;
			
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = userService.read(id);
			request.setAttribute("dto", dto);
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/user/readuser.jsp").forward(request, response);	
			} else {
				updateCompanyAllList(request);
				updateUsertypeList(request);
				getServletContext().getRequestDispatcher("/user/updateuser.jsp").forward(request, response);
			}
			break;

		case "INSERT":
			username = request.getParameter("username").toString();
			password = request.getParameter("password").toString();
			usertype = request.getParameter("usertype").toString();
			String[] res = request.getParameter("company").toString().split(":");
			int companyid = Integer.parseInt(res[0]);
			String company = res[1];
			dto = new UserDTO (username,password,usertype,companyid, company);
			ans = userService.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			updateCompanyAllList(request);
			updateUsertypeList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			username = request.getParameter("username");
			password = request.getParameter("password");
			usertype = request.getParameter("usertype");
			res = request.getParameter("company").toString().split(":");
			companyid = Integer.parseInt(res[0]);
			company = res[1];
			id = Integer.parseInt(request.getParameter("id"));
			dto = new UserDTO (id, username, password, usertype, companyid, company);
			ans = userService.update(dto);
			updateList(request);
			updateCompanyAllList(request);
			updateUsertypeList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = userService.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			updateCompanyAllList(request);
			updateUsertypeList(request);
			getServletContext().getRequestDispatcher("/user/usermanager.jsp").forward(request, response);
			break;
		}
	}
}