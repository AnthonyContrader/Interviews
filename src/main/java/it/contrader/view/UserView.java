package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;


/**
 * 
 * @author Vittorio
 *
 * Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UserView extends AbstractView {

	private Request request;
	private String choice;

	public UserView() {
		
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
		boolean correct=false;
		while(!correct) {
			System.out.println("          Scegli l'operazione da effettuare:");
			System.out.println("[T]utto [L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]logout");
			this.choice = getInput().toUpperCase();
			switch (choice) {
		        case "T":
		        case "L":
		        case "I":
		        case "M":
		        case "C":
		        case "B":
		        case "E":
		        	correct = true;
		        	break;
	      	default:
	      		System.out.println("Comando Sconosciuto");
			}
		}
	}
	
	/**
	 * Impacchetta la request e la manda allo UserController.
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("User", "doControl", this.request);
	}

}
