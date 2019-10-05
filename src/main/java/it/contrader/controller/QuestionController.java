package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.converter.RecruiterConverter;
import it.contrader.dto.CompanyNameAndIdDTO;
import it.contrader.dto.QuestionDTO;
import it.contrader.dto.RecruiterDTO;
import it.contrader.dto.RecruiterNameAndIdDTO;
import it.contrader.model.Company;
import it.contrader.model.Recruiter;
import it.contrader.services.CompanyService;
import it.contrader.services.QuestionService;
import it.contrader.services.RecruiterService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/Question")
public class QuestionController {
	private final QuestionService questionService;
	private final CompanyService companyService;
	private final RecruiterService recruiterService;
	
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
	
	private void getRecruiters(HttpServletRequest request){
		List<RecruiterNameAndIdDTO> allRecruiter = this.recruiterService.getAllRecruiterNameAndIdOrderByNameAsc();
		request.setAttribute("allRecruiterDTO", allRecruiter);
	}
	
	private void getCompanies(HttpServletRequest request) {
		List<CompanyNameAndIdDTO> allCompany = companyService.getAllCompanyNameAndIdOrderByNameAsc();
		request.setAttribute("allCompanyDTO", allCompany);
	}
	
	
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		visualQuestion(request);
		getRecruiters(request);
		return "question/management";		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update_get(HttpServletRequest request) {
		QuestionDTO question = this.questionService.getQuestionDTOById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("questionDTO", question);
		getRecruiters(request);
		return "question/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update_post(HttpServletRequest request) {
		QuestionDTO question = this.questionService.getQuestionDTOById(Integer.parseInt(request.getParameter("id")));
		RecruiterDTO recruiterDTO = recruiterService.getRecruiterDTOById(Integer.parseInt(request.getParameter("recruiter")));
		Recruiter recruiter = RecruiterConverter.toEntity(recruiterDTO);
		Company company = recruiter.getCompany();
		String sector = company.getSector();
		question.setQuestion(request.getParameter("question"));
		question.setArgument(request.getParameter("argument"));
		question.setSector(sector);
		question.setRecruiter(recruiter);
		question.setCompany(company);
		
		this.questionService.updateQuestion(question);
		visualQuestion(request);
		getRecruiters(request);
		return "question/management";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search_get(HttpServletRequest request) {
		List<QuestionDTO> questions = new ArrayList<QuestionDTO>();
		request.setAttribute("questionResultList", questions);
		getRecruiters(request);
		getCompanies(request);
		request.setAttribute("sectorDistinctList", companyService.getDistinctSectorOrderAsc());
		return "question/search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search_post(HttpServletRequest request) {
		String question = request.getParameter("text");
		String argument = request.getParameter("argument");
		String sector = request.getParameter("sector");
		String recruiterId = request.getParameter("recruiterId");
		String companyId = request.getParameter("companyId");
		List<QuestionDTO> questions = this.questionService.findQuestionByAll("%"+question+"%", "%"+argument+"%", sector, recruiterId, companyId);
		request.setAttribute("questionResultList", questions);
		getRecruiters(request);
		getCompanies(request);
		request.setAttribute("sectorDistinctList", companyService.getDistinctSectorOrderAsc());
		return "question/search";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request) {
		QuestionDTO question = this.questionService.getQuestionDTOById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("questionDTO", question);
		return "question/read";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		this.questionService.deleteQuestionById(id);
		visualQuestion(request);
		getRecruiters(request);
		return "question/management";		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		String question = request.getParameter("question");
		String argument = request.getParameter("argument");		
		RecruiterDTO recruiterDTO = recruiterService.getRecruiterDTOById(Integer.parseInt(request.getParameter("recruiter")));
		Recruiter recruiter = RecruiterConverter.toEntity(recruiterDTO);			
		Company company = recruiter.getCompany();
		String sector = company.getSector();
		
		QuestionDTO questionObj = new QuestionDTO(0, question, argument, sector, recruiter, company);		
		questionService.insertQuestion(questionObj);
		visualQuestion(request);
		getRecruiters(request);
		return "question/management";
	}
}
