package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class UserInsertView extends AbstractView{
	private Request request;
	private String register;
	private String username;
	private String password;
	private String usertype;
	private final String mode = "INSERT";

	public UserInsertView() {
	}
	
	/**
	 * Se la request non è nulla (ovvero se si arriva dalla mode INSERT del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			if (request.getParameters().containsKey("register")) {
				register="register";
			}
			if (request.getParameters().containsKey("mode")) {
				System.out.println("Inserimento andato a buon fine.\n");
				if(request.getParameters().containsKey("register"))
					MainDispatcher.getInstance().callView("Start", null);
				else
					MainDispatcher.getInstance().callView("User", null);
			}
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da inserire
	 */
	@Override
	public void showOptions() {
			System.out.println("Inserisci username dell'utente:");
			username = getInput();
			System.out.println("Inserisci password dell'utente:");
			password = getInput();
			System.out.println("Inserisci l'usertype:");
			System.out.println("[R]ecruiter [C]lient");
			switch (getInput().toUpperCase()) {
				case "R":
					usertype="RECRUITER";
					break;
				case "C":
					usertype="CLIENT";
					break;
			}
	}

	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		if(register!=null)
			request.put("register", "register");
		request.put("username", username);
		request.put("password", password);
		request.put("usertype", usertype);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("User", "doControl", request);
	}


}
