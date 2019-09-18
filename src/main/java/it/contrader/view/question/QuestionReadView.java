package it.contrader.view.question;

import it.contrader.controller.Request;
import it.contrader.dto.QuestionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class QuestionReadView extends AbstractView {

	private int id;
	private Request request;
	private final String mode = "READ";

	public QuestionReadView() {
	}

	/**
	 * Se la request è null (ovvero quando arriva dal controller con mode GETCHOICE e choice L 
	 * il metodo è vuoto.
	 * 
	 * Altrimenti se arriva con uno user nella request (ovvero quando arriva
	 * dal controller con mode READ) mostra lo user. In questo caso torna alla UserView senza eseguire
	 * gli altri due metodi
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("\n------------------- Gestione domande ----------------\n");
			System.out.println("ID\tDomanda\tSettore\tAzienda");
			System.out.println("----------------------------------------------------\n");
			QuestionDTO question = (QuestionDTO) request.get("question");
			System.out.println(question);
			MainDispatcher.getInstance().callView("Question", null);
		}
	}

	
	/**
	 * chiede all'utente di inserire l'id dell'utente da leggere
	 */
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID della domanda:");
		id = Integer.parseInt(getInput());
	}

	/**
	 * impacchetta una request con l'id dell'utente da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Question", "doControl", request);
	}

}
