package it.contrader.view.question;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class QuestionInsertView extends AbstractView{
	private Request request;

	private String question;
	private String sector;
	private int companyid;
	private final String mode = "INSERT";
	private String usertype;

	public QuestionInsertView() {
	}
	
	/**
	 * Se la request non ï¿½ nulla (ovvero se si arriva dalla mode INSERT del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			usertype = request.get("usertype").toString();
			if (request.getParameters().containsKey("mode")) {
				System.out.println("Inserimento andato a buon fine.\n");
				MainDispatcher.getInstance().callView("Question", request);
			}
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da inserire
	 */
	@Override
	public void showOptions() {
		boolean correct=false;
		while(!correct) {
			System.out.println("Inserisci la domanda:");
			question = getInput();
			if (!question.isEmpty())
				correct=true;
			else
				System.out.println("Il campo Domanda non può essere vuoto");
		}
		correct=false;
		while(!correct) {
			System.out.println("Inserisci il settore:");
			sector = getInput();
			if (!sector.isEmpty())
				correct=true;
			else
				System.out.println("Il campo Settore non può essere vuoto");
		}
		correct = false;
		while(!correct) {
			System.out.println("Inserisci l'id dell'azienda:");
			try {
			    companyid = Integer.parseInt(getInput());
			    correct = true;
			}catch(NumberFormatException ex) {
				System.out.println("L'id dell'azienda deve essere un numero.");
			}
		}
	}

	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("question", question);
		request.put("sector", sector);
		request.put("companyid", companyid);
		request.put("mode", mode);
		request.put("usertype", usertype);
		MainDispatcher.getInstance().callAction("Question", "doControl", request);
	}


}
