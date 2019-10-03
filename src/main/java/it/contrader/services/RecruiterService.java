package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.RecruiterConverter;
import it.contrader.dao.RecruiterRepository;
import it.contrader.dto.RecruiterDTO;
import it.contrader.model.Recruiter;

@Service
public class RecruiterService {
	private final RecruiterRepository recruiterRepository;
	
	@Autowired
	public RecruiterService (RecruiterRepository recruiterRepository) {
		this.recruiterRepository = recruiterRepository;
	}
	
	public List<RecruiterDTO> getListRecruiterDTO() {
		return RecruiterConverter.toListDTO((List<Recruiter>) recruiterRepository.findAll());
	}
	
	public List<RecruiterDTO> getListAllRecruiterOrderByNameAsc() {
		return RecruiterConverter.toListDTO((List<Recruiter>) recruiterRepository.findByAllOrderByNameAsc("%", "%"));
	}
	
	public RecruiterDTO getRecruiterDTOById(Integer id) {
		return RecruiterConverter.toDTO(recruiterRepository.findById(id).get());
	}
	
	public List<RecruiterDTO> getAllByAllOrderByNameAsc (String name, String companyId) {
		return RecruiterConverter.toListDTO(recruiterRepository.findByAllOrderByNameAsc(name, companyId));
	}
	
	public boolean insertRecruiter (RecruiterDTO recruiterDTO) {
		return recruiterRepository.save(RecruiterConverter.toEntity(recruiterDTO)) != null;
	}

	public boolean updateRecruiter(RecruiterDTO recruiterDTO) {
		return recruiterRepository.save(RecruiterConverter.toEntity(recruiterDTO)) != null;
	}
	
	public void deleteRecruiterById(Integer id) {
		recruiterRepository.deleteById(id);
	}
	
	
}
