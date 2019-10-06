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
import it.contrader.utils.Formatting;

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

	private void getAllQuestion(HttpServletRequest request) {
		List<QuestionDTO> allQuestionDTOList = this.questionService.getListQuestionDTO();
		request.setAttribute("allQuestionList", allQuestionDTOList);
	}
	
	private void getRecruiters(HttpServletRequest request){
		List<RecruiterNameAndIdDTO> allRecruiterDTOList = this.recruiterService.getAllRecruiterNameAndIdOrderByNameAsc();
		request.setAttribute("allRecruiterList", allRecruiterDTOList);
	}
	
	private void getCompanies(HttpServletRequest request) {
		List<CompanyNameAndIdDTO> allCompanyDTOList = companyService.getAllCompanyNameAndIdOrderByNameAsc();
		request.setAttribute("allCompanyList", allCompanyDTOList);
	}
	
	private void getDistinctTopic (HttpServletRequest request) {
		List<String> topicList = questionService.getDistinctTopicOrderAsc();
		request.setAttribute("topicDistinctList", topicList);
	}
	
	private void getDistinctSector (HttpServletRequest request) {
		List<String> sectorList = companyService.getDistinctSectorOrderAsc();
		request.setAttribute("sectorDistinctList", sectorList);
	}
	
	private void updateSearchLists (HttpServletRequest request) {
		getRecruiters(request);
		getCompanies(request);
		getDistinctTopic (request);
		getDistinctSector (request);
	}
	
	private void updateManagementLists (HttpServletRequest request) {
		getAllQuestion(request);
		getRecruiters(request);
		getDistinctTopic (request);
	}
	
	private QuestionDTO getFormattedQuestionDTOFromRequest (HttpServletRequest request, Integer id) {
		String question = Formatting.formatQuestionText(request.getParameter("question"));
		String topic = request.getParameter("topic");
		if (topic.equals("#"))
			topic = Formatting.formatQuestionTopic(request.getParameter("otherTopic"));
		RecruiterDTO recruiterDTO = recruiterService.getRecruiterDTOById(Integer.parseInt(request.getParameter("recruiter")));
		Recruiter recruiter = RecruiterConverter.toEntity(recruiterDTO);
		Company company = recruiter.getCompany();
		String sector = company.getSector();
		return new QuestionDTO(id, question, topic, sector, recruiter, company);	
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		updateManagementLists (request);
		return "question/management";		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update_get(HttpServletRequest request) {
		QuestionDTO questionDTO = this.questionService.getQuestionDTOById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("question", questionDTO);
		getRecruiters(request);
		getDistinctTopic (request);
		return "question/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update_post(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		QuestionDTO questionDTO = getFormattedQuestionDTOFromRequest(request, id);
		questionService.updateQuestion(questionDTO);
		updateManagementLists (request);
		return "question/management";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search_get(HttpServletRequest request) {
		List<QuestionDTO> questionsDTOList = new ArrayList<QuestionDTO>();
		request.setAttribute("questionResultList", questionsDTOList);
		updateSearchLists (request);
		return "question/search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search_post(HttpServletRequest request) {
		String question = request.getParameter("text");
		String topic = request.getParameter("topic");
		String sector = request.getParameter("sector");
		String recruiterId = request.getParameter("recruiterId");
		String companyId = request.getParameter("companyId");
		List<QuestionDTO> questionsDTOList = questionService.findQuestionByAll("%"+question+"%", "%"+topic+"%", sector, recruiterId, companyId);
		request.setAttribute("questionResultList", questionsDTOList);
		updateSearchLists (request);
		return "question/search";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request) {
		QuestionDTO questionDTO = this.questionService.getQuestionDTOById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("question", questionDTO);
		return "question/read";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		this.questionService.deleteQuestionById(id);
		updateManagementLists (request);
		return "question/management";		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		QuestionDTO questionDTO = getFormattedQuestionDTOFromRequest(request, 0);		
		questionService.insertQuestion(questionDTO);
		updateManagementLists (request);
		return "question/management";
	}
}
