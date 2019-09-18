package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.QuestionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;


/**
 * 
 * @author Vittorio
 *
 * Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class QuestionView extends AbstractView {

	private Request request;
	private String choice;

	public QuestionView() {
		
	}

	/**
	 * Mostra la lista utenti
	 */
	@Override
	public void showResults(Request request) {
		
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi UserController). 
	 * Mette la modalità GETCHOICE nella mode.
	 */
	@Override
	public void showOptions() {
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[T]utto [L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]logout");

		this.choice = getInput();

		
	}
	
	/**
	 * Impacchetta la request e la manda allo UserController.
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("Question", "doControl", this.request);
	}

}
