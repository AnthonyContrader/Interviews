package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.QuestionDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.UserService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/User")
public class UserController {

	private final UserService userService;
	private HttpSession session;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	private void visualUser(HttpServletRequest request){
		List<UserDTO> allUser = this.userService.getListaUserDTO();
		request.setAttribute("allUserDTO", allUser);
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public String management(HttpServletRequest request) {
		visualUser(request);
		return "user/management";		
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request) {
		Integer id =Integer.parseInt(request.getParameter("id"));
		UserDTO userDTO = userService.getUserDTOById(id);
		request.setAttribute("userDTO", userDTO);
		return "user/read";		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		this.userService.deleteUserById(id);
		visualUser(request);
		return "user/management";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search_get(HttpServletRequest request) {
		List<UserDTO> users = new ArrayList<UserDTO>();
		request.setAttribute("userResultList", users);
		return "user/search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search_post(HttpServletRequest request) {
		String username = request.getParameter("username");
		String userType = request.getParameter("userType");
		String email = request.getParameter("email");
		List<UserDTO> users = this.userService.getAllByAll("%"+username+"%", "%"+userType+"%", "%"+email+"%");
		request.setAttribute("userResultList", users);
		return "user/search";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update_get(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
				UserDTO userDTO = userService.getUserDTOById(id);
				request.setAttribute("userDTO", userDTO);
		return "user/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update_post(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String email = request.getParameter("email");
		UserDTO userDTO= new UserDTO(id, username, password, userType,email);
		userService.updateUser(userDTO);
		visualUser(request);
		return "user/management";
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String email = request.getParameter("email");
		UserDTO userObj = new UserDTO(0, username, password, userType, email);
		userService.insertUser(userObj);
		visualUser(request);
		return "user/management";
	}
}
