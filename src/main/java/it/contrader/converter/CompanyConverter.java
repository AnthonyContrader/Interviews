package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CompanyDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Company;
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

	/**
	 * Crea un oggetto di tipo CompanyDTO e lo riempie con i campi del parametro company
	 * di tipo Company. Notare l'uso del metodo get() per ottenere il valore
	 * dell'attributo-
	 */

	public CompanyDTO toDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (company != null) {
			companyDTO.setId(company.getId());
			companyDTO.setName(company.getName());
			companyDTO.setAddress(company.getAddress());
			companyDTO.setCity(company.getCity());
		}
		return companyDTO;
	}

	/**
	 * Crea un oggetto di tipo Company e lo riempie con i campi del parametro company di
	 * tipo CompanyDTO. Notare l'uso del metodo get() per ottenere il valore
	 * dell'attributo-
	 */

	public Company toEntity(CompanyDTO companyDTO) {
		Company company = new Company();
		if (companyDTO != null) {
			company.setId(companyDTO.getId());
			company.setName(companyDTO.getName());
			company.setAddress(companyDTO.getAddress());
			company.setCity(companyDTO.getCity());
		}
		return company;
	}

	/**
	 * Metodo per convertire le liste di Company.
	 */

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
