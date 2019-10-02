package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Question;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

	public List<Question> findByQuestionAndArgumentAndSectorAndRecruiterIdAndCompanyId(String question, String argument, String sector, Integer recruiterId, Integer companyId);
	
	public List<Question> findByArgument(String argument);
}
