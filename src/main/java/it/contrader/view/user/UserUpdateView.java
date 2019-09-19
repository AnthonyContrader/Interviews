package it.contrader.view.user;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class UserUpdateView extends AbstractView {
	private Request request;

	private int id;
	private String username;
	private String password;
	private String usertype;
	private final String mode = "UPDATE";

	public UserUpdateView() {
	}

	/**
	 * Se la request non è nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("User", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		boolean correct=false;
		while(!correct) {
			System.out.println("Inserisci id dell'utente:");
			try {
				id = Integer.parseInt(getInput());
				correct=true;
			} catch (NumberFormatException e) {
				System.out.println("L'id deve essere un numero");
			}
		}
		correct=false;
		while(!correct) {
			System.out.println("Inserisci username dell'utente:");
			username = getInput();
			if (!username.isEmpty())
				correct=true;
			else
				System.out.println("Il campo Username non può essere vuoto");
		}
		correct=false;
		while(!correct) {
			System.out.println("Inserisci password dell'utente:");
			password = getInput();
			if (!password.isEmpty())
				correct=true;
			else
				System.out.println("Il campo Password non può essere vuoto");
		}
		correct=false;
		while(!correct) {
			System.out.println("Inserisci l'usertype:");
			System.out.println("[R]ecruiter [C]lient");
			switch (getInput().toUpperCase()) {
				case "R":
					usertype="RECRUITER";
					correct=true;
					break;
				case "C":
					usertype="CLIENT";
					correct=true;
					break;
				default:
	        		System.out.println("Usertype Sconosciuto");	
			}
		}
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("username", username);
		request.put("password", password);
		request.put("usertype", usertype);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("User", "doControl", request);
	}

}
