package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UserDTO;
import it.contrader.dto.UserRecruiterDTO;
import it.contrader.dto.UserTypeDistinctDTO;
import it.contrader.model.User;
import it.contrader.model.UserRecruiter;
import it.contrader.model.UserTypeDistinct;

/**
 * 
 * @author Vittorio
 * 
 * Implementando questa l'interfaccia converter la classe UserConverter � OBBLIGATA ad implementarne i metodi
 *
 */
public class UserConverter {
	public UserConverter () {
		
	}

	public UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		if (user != null) {
			userDTO.setId(user.getId());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setUsertype(user.getUsertype());
			userDTO.setCompanyid(user.getCompanyid());
			userDTO.setCompany(user.getCompany());
		}
		return userDTO;
	}

	public User toEntity(UserDTO userDTO) {
		User user = new User();
		if (userDTO != null) {
			user.setId(userDTO.getId());
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			user.setUsertype(userDTO.getUsertype());
			user.setCompanyid(userDTO.getCompanyid());
			user.setCompany(userDTO.getCompany());
		}
		return user;
	}

	public List<UserRecruiterDTO> recruiterToDTOList(List<UserRecruiter> UserRecruiterList) {
		List<UserRecruiterDTO> userRecruiterDTOList = new ArrayList<UserRecruiterDTO>();
		for (UserRecruiter userRecruiter : UserRecruiterList) {
			UserRecruiterDTO userRecruiterDTO = new UserRecruiterDTO();
			userRecruiterDTO.setId(userRecruiter.getId());
			userRecruiterDTO.setName(userRecruiter.getName());
			userRecruiterDTOList.add(userRecruiterDTO);
		}
		return userRecruiterDTOList;
	}
	
	public List<UserTypeDistinctDTO> userTypeDistinctToDTOList(List<UserTypeDistinct> userTypeDistinctList) {
		List<UserTypeDistinctDTO> userTypeDistinctDTOList = new ArrayList<>();
		for (UserTypeDistinct userTypeDistinct : userTypeDistinctList) {
			UserTypeDistinctDTO userTypeDistinctDTO = new UserTypeDistinctDTO();
			userTypeDistinctDTO.setUsertype(userTypeDistinct.getUsertype());
			userTypeDistinctDTOList.add(userTypeDistinctDTO);
		}
		return userTypeDistinctDTOList;
	}
	
	public List<UserDTO> toDTOList(List<User> userList) {
		//Crea una lista vuota.
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(User user : userList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			userDTOList.add(toDTO(user));
		}
		return userDTOList;
	}

	
	
}
