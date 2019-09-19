/**
 * Manage a Business Owner view
 */

package it.contrader.view;


/**
 * Per Ulteriori dettagli vedi Guida sez 9 View.
 * Per la descrizione dei metodi vedi l'interfaccia View in questo pacchetto.
 */
import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeAdminView extends AbstractView {

    private String choice;
    private String usertype;
	private Request request;

	/**
	 * Se la request non � nulla mostra un messaggio di benvenuto
	 */
    public void showResults(Request request) {
    	if(request!=null) {
    		usertype = request.get("usertype").toString();
    		if(request.getParameters().containsKey("username"))
    			System.out.println("\n Benvenuto in SAMPLE PROJECT "+request.get("username").toString() + "\n");
    	}
    }


    /**
     * Chiede all'utente di effettuare una scelta (da console)
     */
    public void showOptions() {
    	boolean correct = false;
		while (!correct) {
	        System.out.println("-------------MENU------------\n");
	        System.out.println(" Seleziona cosa vuoi gestire:");
	        System.out.println("[U]tenti [A]ziende [D]omande [E]logout");
	        //Il metodo che salva l'input nella stringa choice.
	        //getInput() � definito in AbstractView.
	        choice = this.getInput().toUpperCase();
	        switch (choice) {
		        case "U":
		        case "A":
		        case "D":
		        case "E":
		        	correct = true;
		        	break;
	        	default:
	        		System.out.println("Comando Sconosciuto");
	        }
		}
    }

    /**
     * Impacchetta una request (in base alla scelta sar� diversa) che invia ai controller tramite il
     * Dispatcher
     */
    public void submit() {    
    	//crea una nuova Request (vedi classe Request)
    	request = new Request();
        switch (choice.toUpperCase()) {
        case "U":
        	request.put("mode", "mode");
        	MainDispatcher.getInstance().callAction("User", "doControl", request);
        	break;
 
        case "A":

        	break;
        	
        case "D":
        	request.put("mode", "mode");
        	request.put("usertype", usertype);
        	MainDispatcher.getInstance().callAction("Question", "doControl", request);
        	break;
        	
        case "E":
        	MainDispatcher.getInstance().callAction("Start", "doControl", null);
        	break;
        default:
        	
            request.put("choice", choice);
        	MainDispatcher.getInstance().callAction("Start", "doControl", request);
        }
    }
}
