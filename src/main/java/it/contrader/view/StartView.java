package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class StartView extends AbstractView {
	private String choice;


	public void showResults(Request request) {

	}
	/**
	 * chiede in input all'utente uno username e una password usando il metodo getInput() presente in AbstractView
	 */
	public void showOptions() {
		
		System.out.println("----- .:Benvenuto:. ----");
		
		boolean correct = false;
		while (!correct) {
			System.out.println("Seleziona cosa vuoi fare: ");
			System.out.println("[R]egistrati [L]ogin");
			this.choice = getInput().toUpperCase();
			switch (choice) {
		        case "R":
		        case "L":
		        	correct = true;
		        	break;
	        	default:
	        		System.out.println("Comando Sconosciuto");
	        }
		}
	}

	/**
	 * Impacchetta una request (metodo request.put("chiave", valore)) e la manda al controller Home tramite il Dispatcher
	 */
	public void submit() {
		
		Request request = new Request();
		
		request.put("choice", choice);
		
		MainDispatcher.getInstance().callAction("Start", "doControl", request);
	}


}
