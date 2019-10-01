package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.CompanyConverter;
import it.contrader.converter.ConverterUser;
import it.contrader.dao.CompanyRepository;
import it.contrader.dto.CompanyDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Company;
import it.contrader.model.User;

@Service
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository=companyRepository;
	}
		
	public List<CompanyDTO>getListCompanyDTO(){
		return CompanyConverter.toListDTO((List<Company>) companyRepository.findAll());
	}
	
	public CompanyDTO getCompanyDTOById(Integer id) {
		return CompanyConverter.toDTO(companyRepository.findById(id).get());
	}
	
	public List<CompanyDTO> getAllByAll (String name,String address,String city,String sector){
		return CompanyConverter.toListDTO(companyRepository.findAllByAll(name, address, city, sector));
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

