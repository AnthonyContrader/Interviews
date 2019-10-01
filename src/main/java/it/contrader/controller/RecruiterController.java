package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.RecruiterDTO;
import it.contrader.services.RecruiterService;

import java.util.List;

@Controller
@RequestMapping("/Recruiter")
public class RecruiterController {
	private final RecruiterService recruiterService;
	private HttpSession session;
	
	@Autowired
	public RecruiterController (RecruiterService recruiterService) {
		this.recruiterService = recruiterService;
	}
	
	private void visualRecruiter(HttpServletRequest request){
		List<RecruiterDTO> allRecruiter = this.recruiterService.getListRecruiterDTO();
		request.setAttribute("allRecruiterDTO", allRecruiter);
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		visualRecruiter(request);
		return "recruiterManagement";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
	//	request.setAttribute("id", id);
		this.recruiterService.deleteRecruiterById(id);
		visualRecruiter(request);
		return "recruiterManagement";
		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletRequest request) {
		String name = request.getParameter("name");
		Integer companyId = Integer.parseInt(request.getParameter("companyId"));
		List<RecruiterDTO> allRecruiter = recruiterService.getAllByAll(name, companyId);
		request.setAttribute("allRecruiterDTO", allRecruiter);
		return "recruiterSearch";

	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		String name = request.getParameter("name");
		Integer companyId = Integer.parseInt(request.getParameter("companyId"));
		RecruiterDTO recruiter = new RecruiterDTO(0, name, companyId);
		recruiterService.insertRecruiter(recruiter);
		visualRecruiter(request);
		return "recruiterManagement";
	}
}
