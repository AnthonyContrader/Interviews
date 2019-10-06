package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.CompanyDTO;
import it.contrader.services.CompanyService;
import it.contrader.utils.Formatting;

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
	
	private void getAllCompany(HttpServletRequest request ) {
		List<CompanyDTO> allCompanyDTOList = companyService.getListCompanyDTO();
		request.setAttribute("allCompanyList",allCompanyDTOList);
	}
	
	private void getDistinctSector (HttpServletRequest request) {
		List<String> sectorList= companyService.getDistinctSectorOrderAsc();
		request.setAttribute("sectorDistinctList", sectorList);
	}
	
	private CompanyDTO getFormattedCompanyDTOFromRequest (HttpServletRequest request, Integer id) {
		String name = Formatting.formatCompanyName(request.getParameter("name"));
		String address = Formatting.formatCompanyAddress(request.getParameter("address"));
		String city = Formatting.formatCompanyAddress(request.getParameter("city"));
		String sector = request.getParameter("sector");
		if (sector.equals("#"))
			sector = Formatting.formatCompanyAddress(request.getParameter("otherSector"));
		return new CompanyDTO(id, name, address, city, sector);
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		getAllCompany(request);
		getDistinctSector (request);
		return "company/management";		
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update_get(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		CompanyDTO companyDTO= companyService.getCompanyDTOById(id);
		request.setAttribute("company", companyDTO);
		getDistinctSector (request);
		return "company/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update_post(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		CompanyDTO companyDTO= getFormattedCompanyDTOFromRequest (request, id);
		companyService.updateCompany(companyDTO);
		getAllCompany(request);
		getDistinctSector (request);
		return "company/management";	
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
//		request.setAttribute("id", id);
		this.companyService.deleteCompanyById(id);
		getAllCompany(request);
		getDistinctSector (request);
		return "company/management";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request) {
		Integer id= Integer.parseInt(request.getParameter("id"));
		CompanyDTO companyDTO= companyService.getCompanyDTOById(id);
		request.setAttribute("company",companyDTO);
		return "company/read";		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search_get(HttpServletRequest request) {
		List<CompanyDTO> companyDTOResultList = new ArrayList<>();
		request.setAttribute("companyResultList", companyDTOResultList);
		getDistinctSector (request);
		return "company/search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search_post(HttpServletRequest request) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String sector = request.getParameter("sector");
		List<CompanyDTO> companyDTOResultList = this.companyService.findCompanyByAllOrderByNameAsc("%"+name+"%", "%"+address+"%", "%"+city+"%", sector);
		request.setAttribute("companyResultList", companyDTOResultList);
		getDistinctSector (request);
		return "company/search";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		CompanyDTO companyDTO = getFormattedCompanyDTOFromRequest (request, 0);
		companyService.insertCompany(companyDTO);
		getAllCompany(request);
		getDistinctSector (request);
		return "company/management";
	}
}

