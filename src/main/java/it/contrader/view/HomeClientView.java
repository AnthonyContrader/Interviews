package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeClientView extends AbstractView{

	String choice;

	@Override
	public void showResults(Request request) {
    	System.out.println("\n Benvenuto in SAMPLE PROJECT "+request.get("username").toString() + "\n");

	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU------------\n");
		System.out.println("NESSUNA OPZIONE DISPONIBILE!");
		System.out.println("\n Esatto, puoi solo uscire...");
		choice = this.getInput();

	}

	@Override
	public void submit() {

		switch (choice) {

		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;

		default:
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
		}
	}

}
