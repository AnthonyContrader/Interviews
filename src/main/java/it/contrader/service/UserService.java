package it.contrader.service;

import java.util.List;

import it.contrader.converter.UserConverter;
import it.contrader.dao.UserDAO;
import it.contrader.dto.UserDTO;

/**
 * 
 * @author Vittorio
 *
 */
public class UserService {
	
	private UserConverter userConverter;

	private UserDAO userDAO;

	/**
	 * Costruisce un oggetto di tipo UserDAO e UserConverter per poterne usare i
	 * metodi
	 */
	public UserService() {
		this.userDAO = new UserDAO();
		this.userConverter = new UserConverter();
	}

	// chiama il metodo del DAO che ottiene una lista di tutti gli user
	public List<UserDTO> getAll() {
		return this.userConverter.toDTOList(this.userDAO.getAll());
	}

	// chiama il metodo del DAO che restituisce uno user in base al suo id
	public UserDTO read(int id) {
		return this.userConverter.toDTO(this.userDAO.read(id));
	}

	// chiama il metodo del DAO che cancella uno user in base al suo id
	public boolean delete(int userId) {
		return userDAO.delete(userId);
	}

	// chiama il metodo del DAO che inserisce un oggetto User
	public boolean insert(UserDTO userDTO) {
		return userDAO.insert(userConverter.toEntity(userDTO));
	}

	// chiama il metodo del DAO che modifica un utente
	public boolean update(UserDTO userDTO) {
		return userDAO.update(userConverter.toEntity(userDTO));
	}
}
