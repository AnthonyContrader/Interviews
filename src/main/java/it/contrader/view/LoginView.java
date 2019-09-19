package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class LoginView extends AbstractView {

	private String username;
	
	private String password;

	public void showResults(Request request) {

	}
	/**
	 * chiede in input all'utente uno username e una password usando il metodo getInput() presente in AbstractView
	 */
	public void showOptions() {
		
		System.out.println("----- .:LOGIN:. ----");
		boolean correct=false;
		while(!correct) {
			System.out.println(" Nome utente:");
			this.username = getInput();
			if (!username.isEmpty())
				correct=true;
			else
				System.out.println("Il campo Utente non può essere vuoto");
		}
		correct=false;
		while(!correct) {
			System.out.println(" Password:");
			this.password = getInput();
			if (!password.isEmpty())
				correct=true;
			else
				System.out.println("Il campo Password non può essere vuoto");
		}
		
	}

	/**
	 * Impacchetta una request (metodo request.put("chiave", valore)) e la manda al controller Home tramite il Dispatcher
	 */
	public void submit() {
		
		Request request = new Request();
		
		request.put("username", username);
		request.put("password", password);
		
		MainDispatcher.getInstance().callAction("Home", "doControl", request);
	}


}
