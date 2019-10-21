package it.contrader.interviews.service.mapper;

import it.contrader.interviews.domain.*;
import it.contrader.interviews.service.dto.RecruiterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Recruiter and its DTO RecruiterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecruiterMapper extends EntityMapper<RecruiterDTO, Recruiter> {



    default Recruiter fromId(Long id) {
        if (id == null) {
            return null;
        }
        Recruiter recruiter = new Recruiter();
        recruiter.setId(id);
        return recruiter;
    }
}
