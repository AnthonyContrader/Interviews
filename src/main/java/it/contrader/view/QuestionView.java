package it.contrader.view;

import it.contrader.controller.Request;
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
	private String usertype;
	
	public QuestionView() {
		
	}

	/**
	 * Mostra la lista utenti
	 */
	@Override
	public void showResults(Request request) {
		if(request!=null) {
			usertype = request.get("usertype").toString();
		}
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi UserController). 
	 * Mette la modalit� GETCHOICE nella mode.
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
		request.put("usertype", usertype);
		MainDispatcher.getInstance().callAction("Question", "doControl", this.request);
	}

}
