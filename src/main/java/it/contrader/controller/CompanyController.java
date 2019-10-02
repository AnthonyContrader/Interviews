package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.CompanyDTO;
import it.contrader.services.CompanyService;

import java.util.List;

@Controller
@RequestMapping("/Company")

public class CompanyController {
	private final CompanyService companyService;
	private HttpSession session;
	
    @Autowired
    public CompanyController(CompanyService companyService) {
    	this.companyService=companyService;
    }
	
	private void visualCompany(HttpServletRequest request ) {
		List<CompanyDTO> allCompany=this.companyService.getListCompanyDTO();
		request.setAttribute("allCompanyDTO",allCompany);
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		visualCompany(request);
		return "company/management";		
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update_get(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		CompanyDTO companyDTO= companyService.getCompanyDTOById(id);
		request.setAttribute("companyDTO", companyDTO);
		return "company/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update_post(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String sector=request.getParameter("sector");
		CompanyDTO companyDTO= new CompanyDTO (id,name,address,city,sector);
		companyService.updateCompany(companyDTO);
		visualCompany(request);
		return "company/management";	
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
//		request.setAttribute("id", id);
		this.companyService.deleteCompanyById(id);
		visualCompany(request);
		return "company/management";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request) {
		Integer id= Integer.parseInt(request.getParameter("id"));
		CompanyDTO companyDTO= companyService.getCompanyDTOById(id);
		request.setAttribute("companyDTO",companyDTO);
		return "company/read";		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletRequest request) {
		final String name = request.getParameter("name");
		final String address = request.getParameter("address");
		final String city = request.getParameter("city");
		final String sector = request.getParameter("sector");
		List<CompanyDTO> allCompany = this.companyService.getAllByAll(name, address, city, sector);
		request.setAttribute("allCompanyDTO", allCompany);
		return "company/search";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		final String name = request.getParameter("name");
		final String address = request.getParameter("address");
		final String city = request.getParameter("city");
		final String sector = request.getParameter("sector");
		CompanyDTO companyObj = new CompanyDTO(0, name, address, city,sector);
		companyService.insertCompany(companyObj);
		visualCompany(request);
		return "company/management";
	}
	
	
}

