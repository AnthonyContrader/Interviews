package it.contrader.servlets;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CompanyDTO;
import it.contrader.dto.CompanySectorDistinctDTO;
import it.contrader.dto.CompanyNameDTO;
import it.contrader.dto.QuestionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.dto.UserRecruiterDTO;
import it.contrader.service.CompanyService;
import it.contrader.service.QuestionService;
import it.contrader.service.UserService;

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
	
	public void getCompanyList(HttpServletRequest request) {
		CompanyService service = new CompanyService();
		List<CompanyDTO> companyListDTO = service.getAll();
		request.setAttribute("companyList", companyListDTO);
	}
	
	public void getCompany(HttpServletRequest request, int id) {
		CompanyService service = new CompanyService();
		CompanyDTO company = service.read(id);
		request.setAttribute("company", company);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionService questionService = new QuestionService();
		CompanyService companyService = new CompanyService();
		UserService userService = new UserService();
		String mode = request.getParameter("mode");
		QuestionDTO dto;
		int id;
		boolean ans;
		String question, sector, recruiter, company, recruiteridString, companyidString;
		int recruiterid, companyid;
		
		if(request.getSession().getAttribute("user")==null) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
			return;
		}
		
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		int usercompanyid = user.getCompanyid();
		String usertype = user.getUsertype();
		
		switch (mode.toUpperCase()) {
		case "QUESTIONLIST":
			updateList(request);			
			getCompany(request,usercompanyid);
			getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
			break;

		case "SEARCH":
			List<QuestionDTO> listDTO = new ArrayList<QuestionDTO> ();
			List<CompanySectorDistinctDTO> sectorDistinctAllList = companyService.getSectorDistinctAll();
			List<UserRecruiterDTO> recruiterAllList = userService.getRecruiterAll();			
			List<CompanyNameDTO> companyAllList = companyService.getCompanyAll();
			if (request.getParameter("search") != null) {
				question = "%" + request.getParameter("word") + "%";
				sector = request.getParameter("sector");
				recruiteridString = request.getParameter("recruiterid");
				companyidString = request.getParameter("companyid");
				listDTO = questionService.search(question, sector, recruiteridString, companyidString);			
				getCompanyList(request);
			}
				request.setAttribute("questionResultList", listDTO);
				request.setAttribute("sectorDistinctAllList", sectorDistinctAllList);
				request.setAttribute("recruiterAllList", recruiterAllList);
				request.setAttribute("companyAllList", companyAllList);
				getServletContext().getRequestDispatcher("/question/searchquestion.jsp").forward(request,  response);
			break;
			
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = questionService.read(id);
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
				ans = questionService.insert(dto);
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
				dto = questionService.read(id);
				if (dto.getRecruiterid()==user.getId()||usertype.equals("ADMIN")) {
					question = request.getParameter("question");
					sector = dto.getSector();
					recruiter = dto.getRecruiter();
					company = dto.getCompany();
					recruiterid = dto.getRecruiterid();
					companyid = dto.getCompanyid();
					dto = new QuestionDTO (id, question, sector, recruiter, company, recruiterid, companyid);
					ans = questionService.update(dto);
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
				dto = questionService.read(id);
				if (dto.getRecruiterid()==user.getId()||usertype.equals("ADMIN")) {
					ans = questionService.delete(id);
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