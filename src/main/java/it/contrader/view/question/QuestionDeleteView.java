package it.contrader.view.question;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class QuestionDeleteView extends AbstractView {
	private Request request;

	private int id;
	private final String mode = "DELETE";
	private String usertype;

	public QuestionDeleteView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode DELETE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			usertype = request.get("usertype").toString();
			if (request.getParameters().containsKey("mode")) {
				System.out.println("Cancellazione andata a buon fine.\n");
				MainDispatcher.getInstance().callView("Question", request);
			}
		}
	}

	/**
	 * Chiede all'utente di inserire l'id dell'utente da cancellare
	 */
	@Override
	public void showOptions() {
		boolean correct=false;
		while(!correct) {
			System.out.println("Inserisci id della domanda:");
			try {
				id = Integer.parseInt(getInput());
				correct=true;
			} catch (NumberFormatException e) {
				System.out.println("L'id deve essere un numero");
			}
		}
	}

	/**
	 * impacchetta la request con l'id dell'utente da cancellare
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		request.put("usertype", usertype);
		MainDispatcher.getInstance().callAction("Question", "doControl", request);
	}


}
