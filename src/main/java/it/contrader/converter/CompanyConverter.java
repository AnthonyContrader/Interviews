package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CompanyDTO;
import it.contrader.dto.CompanyNameDTO;
import it.contrader.dto.CompanySectorDistinctDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Company;
import it.contrader.model.CompanyName;
import it.contrader.model.CompanySectorDistinct;
import it.contrader.model.User;


/**
 * 
 * @author Vittorio
 * 
 *         Implementando questa l'interfaccia converter la classe CompanyConverter
 *         è OBBLIGATA ad implementarne i metodi
 *
 */
public class CompanyConverter {
	public CompanyConverter () {
		
	}
	
	public CompanyDTO toDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (company != null) {
			companyDTO.setId(company.getId());
			companyDTO.setName(company.getName());
			companyDTO.setAddress(company.getAddress());
			companyDTO.setCity(company.getCity());
			companyDTO.setSector(company.getSector());
		}
		return companyDTO;
	}
	public Company toEntity(CompanyDTO companyDTO) {
		Company company = new Company();
		if (companyDTO != null) {
			company.setId(companyDTO.getId());
			company.setName(companyDTO.getName());
			company.setAddress(companyDTO.getAddress());
			company.setCity(companyDTO.getCity());
			company.setSector(companyDTO.getSector());
		}
		return company;
	}

	public List<CompanyNameDTO> nameToDTOList(List<CompanyName> companyNameList) {
		List<CompanyNameDTO> companyNameDTOList = new ArrayList<CompanyNameDTO>();
		for (CompanyName companyName : companyNameList) {
			CompanyNameDTO companyNameDTO = new CompanyNameDTO();
			companyNameDTO.setId(companyName.getId());
			companyNameDTO.setName(companyName.getName());
			companyNameDTOList.add(companyNameDTO);
		}
		return companyNameDTOList;
	}

	public List<CompanySectorDistinctDTO> sectorDistinctToDTOList(List<CompanySectorDistinct> companySectorDistinctList) {
		List<CompanySectorDistinctDTO> companySectorDistinctDTOList = new ArrayList<CompanySectorDistinctDTO>();
		for (CompanySectorDistinct companySectorDistinct : companySectorDistinctList) {
			CompanySectorDistinctDTO companySectorDistinctDTO = new CompanySectorDistinctDTO();
			companySectorDistinctDTO.setSector(companySectorDistinct.getSector());
			companySectorDistinctDTOList.add(companySectorDistinctDTO);
		}
		return companySectorDistinctDTOList;
	}
	
	public List<CompanyDTO> toDTOList(List<Company> companyList) {
		// Crea una lista vuota.
		List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>();

		// Cicla tutti gli elementi della lista e li converte uno a uno
		for (Company company : companyList) {
			// Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			// e lo aggiunge adda lista di DTO
			companyDTOList.add(toDTO(company));
		}
		return companyDTOList;
	}

}
