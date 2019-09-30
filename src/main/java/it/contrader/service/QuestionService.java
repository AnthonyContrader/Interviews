package it.contrader.service;

import java.util.List;

import it.contrader.converter.QuestionConverter;
import it.contrader.dao.QuestionDAO;
import it.contrader.dto.QuestionDTO;

/**
 * 
 * @author Vittorio
 *
 */
public class QuestionService {
	
	private QuestionConverter questionConverter;
	private QuestionDAO questionDAO;

	/**
	 * Costruisce un oggetto di tipo QuestionDAO e QuestionConverter per poterne usare i
	 * metodi
	 */
	public QuestionService() {
		this.questionDAO = new QuestionDAO();
		this.questionConverter = new QuestionConverter();
	}

	// chiama il metodo del DAO che ottiene una lista di tutti gli question
	public List<QuestionDTO> getAll() {
		return this.questionConverter.toDTOList(this.questionDAO.getAll());
	}
	
	public List<QuestionDTO> search(String questionText, String sector, String recruiterid, String companyid) {
		return this.questionConverter.toDTOList(this.questionDAO.search(questionText, sector, recruiterid, companyid));
	}

	// chiama il metodo del DAO che restituisce uno question in base al suo id
	public QuestionDTO read(int id) {
		return this.questionConverter.toDTO(this.questionDAO.read(id));
	}

	// chiama il metodo del DAO che cancella uno question in base al suo id
	public boolean delete(int questionId) {
		return questionDAO.delete(questionId);
	}

	// chiama il metodo del DAO che inserisce un oggetto Question
	public boolean insert(QuestionDTO questionDTO) {
		return questionDAO.insert(questionConverter.toEntity(questionDTO));
	}

	// chiama il metodo del DAO che modifica un utente
	public boolean update(QuestionDTO questionDTO) {
		return questionDAO.update(questionConverter.toEntity(questionDTO));
	}
}
