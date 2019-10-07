package it.contrader.controller;

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
			session.setAttribute("prevPage", request.getParameter("prevPage"));
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
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register_get(HttpServletRequest request) {
		System.out.println("ok");
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register_post(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = "USER";
		String email = request.getParameter("email");
		UserDTO userDTO = new UserDTO(0, username, password, userType, email);
		userService.insertUser(userDTO);
		return "index";
	}
/*	
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(HttpServletRequest request) {
		session = request.getSession();
		String prevPage = (String) session.getAttribute("prevPage");
		if (prevPage.equals("index"))
			return logout(request);
		String[] splittedPrevPage = prevPage.split("/");
		String EntityName = StringUtils.capitalize(splittedPrevPage[0]);
		String controllerName = "it.contrader.controller." + EntityName + "Controller";
		System.out.println(controllerName);
		String methodName = splittedPrevPage[1];
		System.out.println(methodName);
		String serviceName = "it.contrader.services." + EntityName + "Service";
		System.out.println(serviceName);
		Constructor<?> controllerConstructor;
		Method method = null;
		Object controller = null;
		String page = null;
		try {
	        Class<?> controllerClass = Class.forName(controllerName);
	        Class<?> serviceClass = Class.forName(serviceName);
			System.out.println("ok0");
			controllerConstructor = controllerClass.getConstructor(serviceClass);
			System.out.println("ok1");
	        controller = controllerConstructor.newInstance(this.getClass().getDeclaredField("userService").get(this));
	        System.out.println("ok2");
	        method = controller.getClass().getMethod(methodName, HttpServletRequest.class);
	        System.out.println("ok3");
	        page = (String) method.invoke(controller, request);
	        System.out.println("ok4");
		} catch (Exception e) {
			System.out.println("no no");
		}
		System.out.println(page);
		return page;
	}
*/
}