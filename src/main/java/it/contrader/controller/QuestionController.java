package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.converter.CompanyConverter;
import it.contrader.converter.RecruiterConverter;
import it.contrader.dto.CompanyDTO;
import it.contrader.dto.QuestionDTO;
import it.contrader.dto.RecruiterDTO;
import it.contrader.model.Company;
import it.contrader.model.Recruiter;
import it.contrader.services.CompanyService;
import it.contrader.services.QuestionService;
import it.contrader.services.RecruiterService;

import java.util.List;


@Controller
@RequestMapping("/Question")
public class QuestionController {

	private final QuestionService questionService;
	private final CompanyService companyService;
	private final RecruiterService recruiterService;
	private HttpSession session;
	
	@Autowired
	public QuestionController(QuestionService questionService, CompanyService companyService, RecruiterService recruiterService) {
		this.questionService = questionService;
		this.companyService = companyService;
		this.recruiterService = recruiterService;
	}

	private void visualQuestion(HttpServletRequest request){
		List<QuestionDTO> allQuestion = this.questionService.getListQuestionDTO();
		request.setAttribute("allQuestionDTO", allQuestion);
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		visualQuestion(request);
		return "questionManagement";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		this.questionService.deleteQuestionById(id);
		visualQuestion(request);
		return "questionManagement";		
	}
		
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletRequest request) {

		final String question = request.getParameter("word");
		final String argument = request.getParameter("argument");
		final String sector = request.getParameter("sector");
		final Integer recruiterId = Integer.parseInt(request.getParameter("recruiterId"));
		final Integer companyId = Integer.parseInt(request.getParameter("companyId"));

		List<QuestionDTO> allQuestion = this.questionService.getAllByAll(question, argument, sector, recruiterId, companyId);
		request.setAttribute("allQuestionDTO", allQuestion);

		return "questionSearch";

	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		String question = request.getParameter("question");
		String argument = request.getParameter("argument");
		String sector = request.getParameter("sector");
		RecruiterDTO recruiterDTO = recruiterService.getRecruiterDTOById(Integer.parseInt(request.getParameter("recruiterId")));
		Recruiter recruiter = RecruiterConverter.toEntity(recruiterDTO);
		CompanyDTO companyDTO = companyService.getCompanyDTOById(Integer.parseInt(request.getParameter("companyId")));
		Company company = CompanyConverter.toEntity(companyDTO);
		
		QuestionDTO questionObj = new QuestionDTO(0, question, argument, sector, recruiter, company);
		
		questionService.insertQuestion(questionObj);

		visualQuestion(request);
		return "questionManagement";
	}
}
