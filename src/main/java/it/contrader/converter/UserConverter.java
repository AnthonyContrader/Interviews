package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UserDTO;
import it.contrader.model.User;

public class UserConverter {

	public static UserDTO toDTO(User user) {
		UserDTO userDTO = null;
		if (user!=null) {
			userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setUserType(user.getUserType());
			userDTO.setEmail(user.getEmail());
		}
		return userDTO;
	}

	public static User toEntity(UserDTO userDTO) {
		User user = null;
	    if(userDTO!=null) {
	    	user = new User();
	    	user.setId(userDTO.getId());
	    	user.setUsername(userDTO.getUsername());
	    	user.setPassword(userDTO.getPassword());
	    	user.setUserType(userDTO.getUserType());
	    	user.setEmail(userDTO.getEmail());
	    }
	    return user;		
	}
	
	public static List<UserDTO> toListDTO(List<User>list){
		List<UserDTO> listUserDTO = new ArrayList<>();
		if(!list.isEmpty()) {
			for(User user : list) {
				listUserDTO.add(UserConverter.toDTO(user));
			}			
		}		
		return listUserDTO;
	}
}



