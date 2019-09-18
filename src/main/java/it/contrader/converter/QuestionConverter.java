package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.QuestionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Question;
import it.contrader.model.User;

/**
 * 
 * @author Vittorio
 * 
 *         Implementando questa l'interfaccia converter la classe UserConverter
 *         è OBBLIGATA ad implementarne i metodi
 *
 */
public class QuestionConverter implements Converter<Question, QuestionDTO> {

	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user
	 * di tipo User. Notare l'uso del metodo get() per ottenere il valore
	 * dell'attributo-
	 */
	@Override
	public QuestionDTO toDTO(Question question) {
		QuestionDTO questionDTO = new QuestionDTO();
		if (question != null) {
			questionDTO.setId(question.getId());
			questionDTO.setQuestion(question.getQuestion());
			questionDTO.setSector(question.getSector());
			questionDTO.setCompanyid(question.getCompanyid());
		}
		return questionDTO;
	}

	/**
	 * Crea un oggetto di tipo User e lo riempie con i campi del parametro user di
	 * tipo UserDTO. Notare l'uso del metodo get() per ottenere il valore
	 * dell'attributo-
	 */
	@Override
	public Question toEntity(QuestionDTO questionDTO) {
		Question question = new Question();
		if (questionDTO != null) {
			question.setId(questionDTO.getId());
			question.setQuestion(questionDTO.getQuestion());
			question.setSector(questionDTO.getSector());
			question.setCompanyid(questionDTO.getCompanyid());
		}
		return question;
	}

	/**
	 * Metodo per convertire le liste di User.
	 */
	@Override
	public List<QuestionDTO> toDTOList(List<Question> questionList) {
		// Crea una lista vuota.
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();

		// Cicla tutti gli elementi della lista e li converte uno a uno
		for (Question question : questionList) {
			// Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			// e lo aggiunge adda lista di DTO
			questionDTOList.add(toDTO(question));
		}
		return questionDTOList;
	}

}
