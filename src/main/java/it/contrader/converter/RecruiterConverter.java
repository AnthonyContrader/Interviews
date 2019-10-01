package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.RecruiterDTO;
import it.contrader.model.Recruiter;

public class RecruiterConverter {
	public static RecruiterDTO toDTO(Recruiter recruiter) {
		RecruiterDTO recruiterDTO = new RecruiterDTO();
		if (recruiter != null) {
			recruiterDTO.setId(recruiter.getId());
			recruiterDTO.setName(recruiter.getName());
			recruiterDTO.setCompanyId(recruiter.getCompanyId());
		}
		return recruiterDTO;
	}
	
	public static Recruiter toEntity(RecruiterDTO recruiterDTO ) {
		Recruiter recruiter = new Recruiter();
		if (recruiterDTO != null) {
			recruiter.setId(recruiterDTO.getId());
			recruiter.setName(recruiterDTO.getName());
			recruiter.setCompanyId(recruiterDTO.getCompanyId());
		}
		return recruiter;
	}
	
	public static List<RecruiterDTO> toListDTO(List<Recruiter> listRecruiter) {
		List<RecruiterDTO> listRecruiterDTO = new ArrayList<>();
		if (!listRecruiter.isEmpty()) {
			for (Recruiter recruiter : listRecruiter) {
				listRecruiterDTO.add(toDTO(recruiter));
			}
		}
		return listRecruiterDTO;
	}

	public static List<Recruiter> toListEntity(List<RecruiterDTO> listRecruiterDTO) {
		List<Recruiter> listRecruiter = new ArrayList<>();
		if (!listRecruiterDTO.isEmpty()) {
			for (RecruiterDTO recruiterDTO : listRecruiterDTO) {
				listRecruiter.add(toEntity(recruiterDTO));
			}
		}
		return listRecruiter;
	}
}
