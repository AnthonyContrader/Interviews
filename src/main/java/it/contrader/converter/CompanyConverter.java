package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CompanyDTO;
import it.contrader.model.Company;


public class CompanyConverter {
	
	public static CompanyDTO toDTO (Company company ) {
			CompanyDTO companyDTO=null;
			if (company!= null) {
				companyDTO= new CompanyDTO ();
				companyDTO.setIdCompany(company.getIdCompany());
				companyDTO.setName(company.getName());
				companyDTO.setAddress(company.getAddress());
				companyDTO.setCity(company.getCity());
				companyDTO.setSector(company.getSector());
			}
			return companyDTO;
	}


	public static Company toEntity (CompanyDTO companyDTO) {
		Company company=null;
		if (companyDTO != null) {
			company=new Company ();
			company.setIdCompany(companyDTO.getIdCompany());
			company.setName(companyDTO.getName());
			company.setAddress(companyDTO.getAddress());
			company.setCity(companyDTO.getCity());
			company.setSector(companyDTO.getSector());
		}
		return company;
		}
	
	public static List<CompanyDTO> toListDTO (List<Company>list){
		List<CompanyDTO>listCompanyDTO=new ArrayList<>();
		if (!list.isEmpty()) {
			for (Company company:list) {
				listCompanyDTO.add(CompanyConverter.toDTO(company));
			}
		}
		return listCompanyDTO;
	}
		
	public static List<Company> toListEntity (List<CompanyDTO> listCompanyDTO){
		List<Company> list=new ArrayList<>();
		if (!listCompanyDTO.isEmpty()) {
			for (CompanyDTO companyDTO: listCompanyDTO) {
				list.add(CompanyConverter.toEntity(companyDTO));
			}
		}
		return list;
		}
}
	
