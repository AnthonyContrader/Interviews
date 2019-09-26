package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CompanyDTO;
import it.contrader.dto.QuestionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.CompanyService;
import it.contrader.service.QuestionService;

/*
 * Per dettagli vedi Guida sez Servlet
 */
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QuestionServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		QuestionService service = new QuestionService();
		List<QuestionDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void getCompany(HttpServletRequest request, int id) {
		CompanyService service = new CompanyService();
		CompanyDTO company = service.read(id);
		request.setAttribute("company", company);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionService service = new QuestionService();
		String mode = request.getParameter("mode");
		QuestionDTO dto;
		int id;
		boolean ans;
		
		if(request.getSession().getAttribute("user")==null) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
			return;
		}
		
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		int usercompanyid = user.getCompanyid();
		String usertype = user.getUsertype();
		
		String question, sector, recruiter, company;
		int recruiterid, companyid;
		
		switch (mode.toUpperCase()) {
		case "QUESTIONLIST":
			updateList(request);			
			getCompany(request,usercompanyid);
			getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			if (request.getParameter("update") == null)
				 getServletContext().getRequestDispatcher("/question/readquestion.jsp").forward(request, response);
			else 
				getServletContext().getRequestDispatcher("/question/updatequestion.jsp").forward(request, response);
			
			break;

		case "INSERT":
			if(!usertype.equals("USER")) {
				question = request.getParameter("question");
				sector = request.getParameter("sector");
				recruiter = request.getParameter("recruiter");
				company = request.getParameter("company");
				recruiterid = Integer.parseInt (request.getParameter("recruiterid"));
				companyid = Integer.parseInt (request.getParameter("companyid"));
				dto = new QuestionDTO (question, sector, recruiter, company, recruiterid, companyid);
				ans = service.insert(dto);
				request.setAttribute("ans", ans);
				updateList(request);
				getCompany(request,usercompanyid);
				getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
				break;
			}else {
				response.sendError(403,"NON AUTORIZZATO");
				return;
			}
			
		case "UPDATE":
			if(!usertype.equals("USER")) {
				id = Integer.parseInt(request.getParameter("id"));
				dto = service.read(id);
				if (dto.getRecruiterid()==user.getId()||usertype.equals("ADMIN")) {
					question = request.getParameter("question");
					sector = dto.getSector();
					recruiter = dto.getRecruiter();
					company = dto.getCompany();
					recruiterid = dto.getRecruiterid();
					companyid = dto.getCompanyid();
					dto = new QuestionDTO (id, question, sector, recruiter, company, recruiterid, companyid);
					ans = service.update(dto);
					updateList(request);
					getCompany(request,usercompanyid);
					getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
					break;
				}else {
					response.sendError(403,"NON AUTORIZZATO");
					return;
				}
			}else {
				response.sendError(403,"NON AUTORIZZATO");
				return;
			}

		case "DELETE":
			if(!usertype.equals("USER")) {				
				id = Integer.parseInt(request.getParameter("id"));
				dto = service.read(id);
				if (dto.getRecruiterid()==user.getId()||usertype.equals("ADMIN")) {
					ans = service.delete(id);
					request.setAttribute("ans", ans);
					updateList(request);
					getCompany(request,usercompanyid);
					getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
					break;
				}else {
					response.sendError(403,"NON AUTORIZZATO");
					return;
				}
			}else {
				response.sendError(403,"NON AUTORIZZATO");
				return;
			}
		}
	}
}