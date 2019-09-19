package it.contrader.controller;

import it.contrader.main.MainDispatcher;

public class StartController implements Controller {

	private static String sub_package = "user.";
	
	public StartController() {
	}

	public void doControl (Request request) {
		/**
		 * Attraverso il Dispatcher va alla pagina di autenticazione
		 */
		if (request!=null) {
			String choice=request.get("choice").toString();
			switch (choice.toUpperCase()) {
				case "R":
					request.put("register", "register");
					MainDispatcher.getInstance().callView(sub_package + "UserInsert", request);
					break;
				case "L":
					MainDispatcher.getInstance().callView("Login", null);
					break;
			}
		} else {
			MainDispatcher.getInstance().callView("Start", null);
		}
	}

}
