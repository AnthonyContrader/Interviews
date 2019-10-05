package it.contrader.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.CompanyConverter;
import it.contrader.dao.CompanyRepository;
import it.contrader.dto.CompanyDTO;
import it.contrader.dto.CompanyNameAndIdDTO;
import it.contrader.model.Company;

@Service
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository=companyRepository;
	}
	
	public List<String> getDistinctSectorOrderAsc (){
		List<String> sectorList = companyRepository.getDistinctSectorOrderAsc();
/*		List<String> sectorList = new ArrayList<> ();
		for(Company company : companyList) {
			sectorList.add(company.getSector());
		}
*/
		return sectorList;
	}
	
	public List<CompanyDTO>getListCompanyDTO(){
		return CompanyConverter.toListDTO((List<Company>) companyRepository.findAll());
	}
	
	public CompanyDTO getCompanyDTOById(Integer id) {
		return CompanyConverter.toDTO(companyRepository.findById(id).get());
	}
	
	public List<CompanyDTO> findCompanyByAllOrderByNameAsc (String name,String address,String city,String sector){
		return CompanyConverter.toListDTO(companyRepository.findCompanyByAllOrderByNameAsc(name, address, city, sector));
	}
	
	public List<CompanyDTO> getAllCompanyOrderByNameAsc() {
		return findCompanyByAllOrderByNameAsc("%", "%", "%", "%");
	}
	
	public List<CompanyNameAndIdDTO> getAllCompanyNameAndIdOrderByNameAsc() {
		return CompanyConverter.toNameAndIdListDTO(companyRepository.findCompanyByAllOrderByNameAsc("%", "%", "%", "%"));
	}
	
	public boolean insertCompany(CompanyDTO companyDTO) {
		return companyRepository.save(CompanyConverter.toEntity(companyDTO)) != null;
	}

	public boolean updateCompany(CompanyDTO companyDTO) {
		return companyRepository.save(CompanyConverter.toEntity(companyDTO)) != null;
	}
	
	public void deleteCompanyById(Integer id) {
		companyRepository.deleteById(id);
	}
}

