package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeRecruiterView extends AbstractView{

	String choice;
	private String usertype;
	
	@Override
	public void showResults(Request request) {
		if(request!=null) {
			usertype=request.get("usertype").toString();
			if(request.getParameters().containsKey("username"))
				System.out.println("\n Benvenuto in SAMPLE PROJECT "+request.get("username").toString() + "\n");
	    }
	}

	@Override
	public void showOptions() {
		boolean correct = false;
		while (!correct) {
			System.out.println("-------------MENU------------\n");
			System.out.println(" Seleziona cosa vuoi gestire:");
			System.out.println("[D]omande [E]logout");
	        choice = this.getInput().toUpperCase();
	        switch (choice) {
	        case "D":
	        case "E":
	        	correct = true;
	        	break;
        	default:
        		System.out.println("Comando Sconosciuto");
	        }
		}
	}

	@Override
	public void submit() {

		switch (choice.toUpperCase()) {
		
		case "D":
			Request request=new Request();
			request.put("usertype", usertype);
			MainDispatcher.getInstance().callView("Question", request);
        	break;

		case "E":
			MainDispatcher.getInstance().callAction("Start", "doControl", null);
			break;

		default:
			MainDispatcher.getInstance().callAction("Start", "doControl", null);
		}
	}

}