package it.contrader.service;

import it.contrader.converter.QuestionConverter;
import it.contrader.dao.QuestionDAO;
import it.contrader.dto.QuestionDTO;
import it.contrader.model.Question;

/**
 * 
 * @author Vittorio
 *
 *Grazie all'ereditarietà mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class QuestionService extends AbstractService<Question, QuestionDTO> {
	
	//Istanzio DAO  e Converter specifici.
	public QuestionService(){
		this.dao = new QuestionDAO();
		this.converter = new QuestionConverter();
	}
	

}
