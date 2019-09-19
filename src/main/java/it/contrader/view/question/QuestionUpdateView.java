package it.contrader.view.question;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class QuestionUpdateView extends AbstractView {
	private Request request;

	private int id;
	private String question;
	private String sector;
	private int companyid;
	private final String mode = "UPDATE";
	private String usertype;

	public QuestionUpdateView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			usertype = request.get("usertype").toString();
			if (request.getParameters().containsKey("mode")) {
				System.out.println("Modifica andata a buon fine.\n");
				MainDispatcher.getInstance().callView("Question", request);
			}
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id della domanda:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci la domanda:");
			question = getInput();
			System.out.println("Inserisci il settore:");
			sector = getInput();
			boolean correct = false;
			while(!correct) {
				System.out.println("Inserisci l'id dell'azienda:");
				try {
				    companyid = Integer.parseInt(getInput());
				    correct = true;
				}catch(NumberFormatException ex) {
					System.out.println("L'id dell'azienda deve essere un numero.");
				}
			}
		} catch (Exception e) {

		}
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("question", question);
		request.put("sector", sector);
		request.put("companyid", companyid);
		request.put("mode", mode);
		request.put("usertype", usertype);
		MainDispatcher.getInstance().callAction("Question", "doControl", request);
	}

}
