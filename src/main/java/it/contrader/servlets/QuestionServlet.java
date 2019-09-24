package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.QuestionDTO;
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

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionService service = new QuestionService();
		String mode = request.getParameter("mode");
		QuestionDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "QUESTIONLIST":
			updateList(request);
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
			String question = request.getParameter("question");
			String sector = request.getParameter("sector").toString();
			int recruiterid = Integer.parseInt (request.getParameter("recruiterid"));
			int companyid = Integer.parseInt (request.getParameter("companyid"));
			dto = new QuestionDTO (question,sector,recruiterid,companyid);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			question = request.getParameter("question");
			sector = request.getParameter("sector");
			recruiterid = Integer.parseInt(request.getParameter("recruiterid"));
			companyid = Integer.parseInt(request.getParameter("companyid"));
			id = Integer.parseInt(request.getParameter("id"));
			dto = new QuestionDTO (id,question, sector, recruiterid, companyid);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/question/questionmanager.jsp").forward(request, response);
			break;
		}
	}
}