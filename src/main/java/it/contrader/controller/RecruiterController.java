package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.converter.CompanyConverter;
import it.contrader.dto.CompanyDTO;
import it.contrader.dto.RecruiterDTO;
import it.contrader.model.Company;
import it.contrader.services.CompanyService;
import it.contrader.services.RecruiterService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Recruiter")
public class RecruiterController {
	private final RecruiterService recruiterService;
	private final CompanyService companyService;
	
	@Autowired
	public RecruiterController (RecruiterService recruiterService, CompanyService companyService) {
		this.recruiterService = recruiterService;
		this.companyService = companyService;
	}
	
	private void visualRecruiter(HttpServletRequest request){
		List<RecruiterDTO> allRecruiter = this.recruiterService.getListRecruiterDTO();
		request.setAttribute("allRecruiterDTO", allRecruiter);
	}
	
	private void getCompanies(HttpServletRequest request) {
		List<CompanyDTO> allCompany = companyService.getAllCompanyOrderByNameAsc();
		request.setAttribute("allCompanyDTO", allCompany);
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		visualRecruiter(request);
		getCompanies(request);
		return "recruiter/management";		
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request) {
		Integer id =Integer.parseInt(request.getParameter("id"));
		RecruiterDTO recruiterDTO = recruiterService.getRecruiterDTOById(id);
		request.setAttribute("recruiterDTO", recruiterDTO);
		return "recruiter/read";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
	//	request.setAttribute("id", id);
		this.recruiterService.deleteRecruiterById(id);
		visualRecruiter(request);
		getCompanies(request);
		return "recruiter/management";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search_get(HttpServletRequest request) {
		List<RecruiterDTO> recruiterDTOList = new ArrayList<>();
		request.setAttribute("allRecruiterDTO", recruiterDTOList);
		getCompanies(request);
		return "recruiter/search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search_post(HttpServletRequest request) {
		String name = request.getParameter("name");
		String companyId = request.getParameter("company");
		List<RecruiterDTO> allRecruiter = recruiterService.findRecruiterByAllOrderByNameAsc("%"+name+"%", companyId);
		request.setAttribute("allRecruiterDTO", allRecruiter);
		getCompanies(request);
		return "recruiter/search";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update_get(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		RecruiterDTO recruiterDTO = recruiterService.getRecruiterDTOById(id);
		request.setAttribute("recruiterDTO", recruiterDTO);
		getCompanies(request);
		return "recruiter/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update_post(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = StringUtils.capitalize(request.getParameter("name").toLowerCase());
		CompanyDTO companyDTO = companyService.getCompanyDTOById(Integer.parseInt(request.getParameter("company")));
		Company company = CompanyConverter.toEntity(companyDTO);
		RecruiterDTO recruiterDTO = new RecruiterDTO(id,name,company);
		recruiterService.updateRecruiter(recruiterDTO);
		visualRecruiter(request);
		getCompanies(request);
		return "recruiter/management";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		String name = StringUtils.capitalize(request.getParameter("name").toLowerCase());
		CompanyDTO companyDTO = companyService.getCompanyDTOById(Integer.parseInt(request.getParameter("company")));
		Company company = CompanyConverter.toEntity(companyDTO);
		RecruiterDTO recruiter = new RecruiterDTO(0, name, company);
		recruiterService.insertRecruiter(recruiter);
		visualRecruiter(request);
		getCompanies(request);
		return "recruiter/management";
	}
}
