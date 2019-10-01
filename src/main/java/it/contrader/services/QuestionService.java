package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.QuestionConverter;
import it.contrader.dao.QuestionRepository;
import it.contrader.dto.QuestionDTO;
import it.contrader.model.Question;

@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	@Autowired
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public List<QuestionDTO> getListQuestionDTO() {
		return QuestionConverter.toListDTO((List<Question>) questionRepository.findAll());
	}

	public QuestionDTO getQuestionDTOById(Integer id) {
		return QuestionConverter.toDTO(questionRepository.findById(id).get());
	}

	
	public boolean insertQuestion(QuestionDTO questionDTO) {
		return questionRepository.save(QuestionConverter.toEntity(questionDTO)) != null;
	}

	public boolean updateQuestion(QuestionDTO questionDTO) {
		return questionRepository.save(QuestionConverter.toEntity(questionDTO)) != null;
	}
	
	public void deleteQuestionById(Integer id) {
		questionRepository.deleteById(id);
	}
	
	public List<QuestionDTO> getAllByAll(String question, String argument, String sector, Integer recruiterId, Integer companyId) {
		
		final List<Question> list = questionRepository.findAllByAll(question, argument, sector, recruiterId, companyId);
		final List<QuestionDTO> questionDTOs = new ArrayList<>();
		list.forEach(i -> questionDTOs.add(QuestionConverter.toDTO(i)));
		return questionDTOs;
	}
	
    public List<QuestionDTO> getAllByArgument(String argument) {
		
		final List<Question> list = questionRepository.findAllByArgument(argument);
		final List<QuestionDTO> questionDTOs = new ArrayList<>();
		list.forEach(i -> questionDTOs.add(QuestionConverter.toDTO(i)));
		return questionDTOs;
	}
}
