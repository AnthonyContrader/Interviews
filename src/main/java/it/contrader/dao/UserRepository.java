package it.contrader.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.contrader.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "SELECT * FROM user WHERE username LIKE ?1 AND user_type LIKE ?2 AND email LIKE ?3",
			nativeQuery = true)
	public List<User> findAllbyAll(String username, String userType, String email);
	
	public User findUserByUsernameAndPassword(String username,String password);
	
}
