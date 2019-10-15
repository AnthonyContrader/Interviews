package it.contrader.interviews.service.mapper;

import it.contrader.interviews.domain.*;
import it.contrader.interviews.service.dto.QuestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Question and its DTO QuestionDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, RecruiterMapper.class})
public interface QuestionMapper extends EntityMapper<QuestionDTO, Question> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    @Mapping(source = "recruiter.id", target = "recruiterId")
    @Mapping(source = "recruiter.name", target = "recruiterName")
    QuestionDTO toDto(Question question);

    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "recruiterId", target = "recruiter")
    Question toEntity(QuestionDTO questionDTO);

    default Question fromId(Long id) {
        if (id == null) {
            return null;
        }
        Question question = new Question();
        question.setId(id);
        return question;
    }
}
