package it.contrader.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Question;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
	
	@Query(value = "SELECT * FROM question AS q LEFT JOIN recruiter AS r ON q.recruiter_Id=r.id LEFT JOIN company AS c ON q.company_Id=c.id WHERE q.question LIKE ?1 AND q.topic LIKE ?2 AND q.sector LIKE ?3 AND r.id LIKE ?4 AND c.id LIKE ?5",
			nativeQuery = true)
	public List<Question> findQuestionByAll(String question, String topic, String sector, String recruiterId, String companyId);
	
	@Query(value = "SELECT DISTINCT topic FROM question ORDER BY topic ASC", nativeQuery = true)
	public List<String> getDistinctTopicOrderAsc();
	
	public List<Question> findQuestionByTopic(String topic);
}
