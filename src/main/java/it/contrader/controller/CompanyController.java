package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.CompanyDTO;
import it.contrader.services.CompanyService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Company")

public class CompanyController {
	private final CompanyService companyService;
	@Autowired
    public CompanyController(CompanyService companyService) {
    	this.companyService=companyService;
    }
	
	private void visualCompany(HttpServletRequest request ) {
		List<CompanyDTO> allCompany=this.companyService.getListCompanyDTO();
		request.setAttribute("allCompanyDTO",allCompany);
	}
	
	private void getDistinctSector (HttpServletRequest request) {
		List<String> sector= companyService.getDistinctSectorOrderAsc();
		request.setAttribute("allDistinctSector", sector);
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		visualCompany(request);
		getDistinctSector (request);
		return "company/management";		
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update_get(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		CompanyDTO companyDTO= companyService.getCompanyDTOById(id);
		request.setAttribute("allCompanyDTO", companyDTO);
		getDistinctSector (request);
		return "company/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update_post(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String name = StringUtils.capitalize(request.getParameter("name").toLowerCase());
		String address = StringUtils.capitalize(request.getParameter("address").toLowerCase());
		String city = StringUtils.capitalize(request.getParameter("city").toLowerCase());
		String sector = request.getParameter("sector");
		if (sector.equals("#"))
			sector = StringUtils.capitalize(request.getParameter("otherSector").toLowerCase());
		CompanyDTO companyDTO= new CompanyDTO (id,name,address,city,sector);
		companyService.updateCompany(companyDTO);
		visualCompany(request);
		getDistinctSector (request);
		return "company/management";	
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
//		request.setAttribute("id", id);
		this.companyService.deleteCompanyById(id);
		visualCompany(request);
		getDistinctSector (request);
		return "company/management";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request) {
		Integer id= Integer.parseInt(request.getParameter("id"));
		CompanyDTO companyDTO= companyService.getCompanyDTOById(id);
		request.setAttribute("allCompanyDTO",companyDTO);
		return "company/read";		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search_get(HttpServletRequest request) {
		List<CompanyDTO> allCompany=new ArrayList<>();
		request.setAttribute("allCompanyDTO", allCompany);
		getDistinctSector (request);
		return "company/search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search_post(HttpServletRequest request) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String sector = request.getParameter("sector");
		List<CompanyDTO> allCompany = this.companyService.findCompanyByAllOrderByNameAsc("%"+name+"%", "%"+address+"%", "%"+city+"%", sector);
		request.setAttribute("allCompanyDTO", allCompany);
		getDistinctSector (request);
		return "company/search";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		String name = StringUtils.capitalize(request.getParameter("name").toLowerCase());
		String address = StringUtils.capitalize(request.getParameter("address").toLowerCase());
		String city = StringUtils.capitalize(request.getParameter("city").toLowerCase());
		String sector = request.getParameter("sector");
		if (sector.equals("#"))
			sector = StringUtils.capitalize(request.getParameter("otherSector").toLowerCase());
		CompanyDTO companyObj = new CompanyDTO(0, name, address, city,sector);
		companyService.insertCompany(companyObj);
		visualCompany(request);
		getDistinctSector (request);
		return "company/management";
	}
	
	
}

