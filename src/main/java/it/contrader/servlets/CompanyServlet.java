package it.contrader.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CompanyDTO;
import it.contrader.dto.CompanySectorDistinctDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.CompanyService;

/*
 * Per dettagli vedi Guida sez Servlet
 */
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompanyServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		CompanyService companyService = new CompanyService();
		request.setAttribute("list", companyService.getAll());
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyService companyService = new CompanyService();
		String mode = request.getParameter("mode");
		CompanyDTO dto;
		String name, address, city, sector;
		int id;
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

		case "COMPANYLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/company/companymanager.jsp").forward(request, response);
			break;

		case "SEARCH":
			List<CompanyDTO> listDTO = new ArrayList<> ();
			List<CompanySectorDistinctDTO> sectorDistinctAllList = companyService.getSectorDistinctAll();
			if (request.getParameter("search") != null) {
				name = "%" + request.getParameter("name") + "%";
				address = "%" + request.getParameter("address") + "%";
				city = "%" + request.getParameter("city") + "%";
				sector = request.getParameter("sector");
				listDTO = companyService.search(name, address, city, sector);			
			}
				request.setAttribute("companyResultList", listDTO);
				request.setAttribute("sectorDistinctAllList", sectorDistinctAllList);
				getServletContext().getRequestDispatcher("/company/searchcompany.jsp").forward(request,  response);
			break;
			
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = companyService.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) 
				 getServletContext().getRequestDispatcher("/company/readcompany.jsp").forward(request, response);
			else
				getServletContext().getRequestDispatcher("/company/updatecompany.jsp").forward(request, response);
			break;

		case "INSERT":
			name = request.getParameter("name");
			address = request.getParameter("address");
			city = request.getParameter("city");
			sector = request.getParameter("sector");
			dto = new CompanyDTO (name,address,city,sector);
			ans = companyService.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/company/companymanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			name = request.getParameter("name");
			address = request.getParameter("address");
			city = request.getParameter("city");
			sector = request.getParameter("sector");
			id = Integer.parseInt(request.getParameter("id"));
			dto = new CompanyDTO (id, name, address, city, sector);
			ans = companyService.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/company/companymanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = companyService.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/company/companymanager.jsp").forward(request, response);
			break;
		}
	}
}