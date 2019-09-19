package it.contrader.view.question;

import java.util.List;

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
public class QuestionAllView extends AbstractView {

	private Request request;
	private final String mode = "QUESTIONLIST";
    private String usertype;
    
	public QuestionAllView() {
	}

	/**
	 * Se la request � null (ovvero quando arriva dal controller con mode GETCHOICE e choice L 
	 * il metodo � vuoto.
	 * 
	 * Altrimenti se arriva con uno user nella request (ovvero quando arriva
	 * dal controller con mode READ) mostra lo user. In questo caso torna alla UserView senza eseguire
	 * gli altri due metodi
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			usertype = request.get("usertype").toString();
			if (request.getParameters().containsKey("questions")) {
				System.out.println("\n------------------- Gestione domande ----------------\n");
				System.out.println("ID\tDomanda\t\t\tSettore\t\t\tAzienda");
				System.out.println("-----------------------------------------------------\n");
				
				@SuppressWarnings("unchecked")
				List<QuestionDTO> questions = (List<QuestionDTO>) request.get("questions");
				for (QuestionDTO u: questions)
					System.out.println(u);
				System.out.println();	
				request=new Request();
				request.put("usertype", usertype);
				MainDispatcher.getInstance().callView("Question", request);
			}			
		}
	}

	
	/**
	 * chiede all'utente di inserire l'id dell'utente da leggere
	 */
	@Override
	public void showOptions() {
		/*System.out.println("Inserisci l'ID della domanda:");
		id = Integer.parseInt(getInput());*/
	}

	/**
	 * impacchetta una request con l'id dell'utente da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("mode", mode);
		request.put("usertype", usertype);
		MainDispatcher.getInstance().callAction("Question", "doControl", request);
	}

}
