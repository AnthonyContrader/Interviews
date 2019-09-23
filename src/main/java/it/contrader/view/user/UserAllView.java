package it.contrader.view.user;

import java.util.List;
import it.contrader.controller.Request;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UserAllView extends AbstractView {

	private Request request;
	private final String mode = "USERLIST";
    
	public UserAllView() {
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
			System.out.println("\n------------------- Gestione utenti ----------------\n");
			System.out.println("Utente\t\t\tTipo\t\t\tPassword\t\t\tID");
			System.out.println("-----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<UserDTO> users = (List<UserDTO>) request.get("users");
			for (UserDTO u: users)
				System.out.println(u);
			System.out.println();	
			request=new Request();
			MainDispatcher.getInstance().callView("User", request);
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
		MainDispatcher.getInstance().callAction("User", "doControl", request);
	}

}
