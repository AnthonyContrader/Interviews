package it.contrader.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.UserDTO;

import it.contrader.service.UserService;


/*
 * Login Servlet
 */
public class RegisterServlet extends HttpServlet {
	// UID della servlet
	private static final long serialVersionUID = 1L;

	/**
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * 
	 * Metodo che gestisce le request che arrivano dalla JSP.
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		session.setAttribute("utente", null);
		UserDTO dto;
		UserService service = new UserService();
		boolean ans;
		
		if (request != null) {
			if(request.getParameter("register")!=null) {
				String username = request.getParameter("username").toString();
				String password = request.getParameter("password").toString();
				String usertype = request.getParameter("usertype").toString();
				dto = new UserDTO (username,password,usertype,0,null);
				ans = service.insert(dto);
				request.setAttribute("ans", ans);
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}else		
			    getServletContext().getRequestDispatcher("/user/register.jsp").forward(request, response);
		}
	}
}
