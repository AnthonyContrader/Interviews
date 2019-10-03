package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.UserDTO;
import it.contrader.services.UserService;

@Controller
@RequestMapping("/Home")
public class HomeController {
	private final UserService userService;
	private HttpSession session;

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginControl(HttpServletRequest request) {
		session = request.getSession();
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");
		final UserDTO userDTO = userService.getByUsernameAndPassword(username, password);
		if(userDTO == null) {
			request.setAttribute("error", true);
			return "index";
		}
		final String userType = userDTO.getUserType();
		if (!StringUtils.isEmpty(userType)) {
			session.setAttribute("user", userDTO);
			if (userType.equals("ADMIN")) {
				return "homeadmin";
			} else if (userType.equals("USER")) {
				return "homeuser";
			}
		}
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		session = request.getSession();
		session.invalidate();
		return "index";

	}
	@RequestMapping(value = "/homeadmin", method = RequestMethod.GET)
	public String homeAdmin(HttpServletRequest request) {
		return "homeadmin";
	}
	
	@RequestMapping(value = "/homeuser", method = RequestMethod.GET)
	public String homeUser(HttpServletRequest request) {
		return "homeuser";
	}
}