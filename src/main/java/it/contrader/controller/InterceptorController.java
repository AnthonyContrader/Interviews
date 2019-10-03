package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import it.contrader.dto.UserDTO;

public class InterceptorController extends HandlerInterceptorAdapter {
		
	@Override
	public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		switch(uri) {
		case "/css/vittoriostyle.css":
		case "/Home/login":
		case "/User/register":
			return true;
		default:			
			if(request.getSession().getAttribute("user")==null) {
				request.getSession().invalidate();
				response.sendRedirect("/");
				return false;
			}
			else {
				UserDTO user = (UserDTO) request.getSession().getAttribute("user");
				if(user.getUserType()=="ADMIN")
					return true;
				else {
					switch(uri.split("/")[1]) {
					case "User":
					case "Recruiter":
					case "Company":
						response.sendError(403,"NON AUTORIZZATO");
						return false;					
					default:
						return true;
					}
				}
			}
		}		
	}
	
}