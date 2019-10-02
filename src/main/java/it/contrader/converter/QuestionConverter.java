package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.QuestionDTO;
import it.contrader.model.Question;

public class QuestionConverter {

	public static QuestionDTO toDTO(Question question) {
		QuestionDTO questionDTO = null;
		if (question != null) {
			questionDTO = new QuestionDTO();
			questionDTO.setId(question.getId());
			questionDTO.setQuestion(question.getQuestion());
			questionDTO.setArgument(question.getArgument());
			questionDTO.setSector(question.getSector());
			questionDTO.setRecruiter(question.getRecruiter());
			questionDTO.setCompany(question.getCompany());
		}
		return questionDTO;
	}

	public static Question toEntity(QuestionDTO questionDTO) {
		Question question = null;
		if (questionDTO != null) {
			question = new Question();
			question.setId(questionDTO.getId());
			question.setQuestion(questionDTO.getQuestion());
			question.setArgument(questionDTO.getArgument());
			question.setSector(questionDTO.getSector());
			question.setRecruiter(questionDTO.getRecruiter());
			question.setCompany(questionDTO.getCompany());
		}
		return question;
	}

	public static List<QuestionDTO> toListDTO(List<Question> list) {
		List<QuestionDTO> listQuestionDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Question question : list) {
				listQuestionDTO.add(QuestionConverter.toDTO(question));
			}
		}
		return listQuestionDTO;
	}

	public static List<Question> toListEntity(List<QuestionDTO> listQuestionDTO) {
		List<Question> list = new ArrayList<>();
		if (!listQuestionDTO.isEmpty()) {
			for (QuestionDTO questionDTO : listQuestionDTO) {
				list.add(QuestionConverter.toEntity(questionDTO));
			}
		}
		return list;
	}
}
