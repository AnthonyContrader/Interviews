package it.contrader.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Company;
import it.contrader.model.User;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company,Integer> {
	@Query(value= "SELECT * FROM company WHERE name LIKE ?1 AND address LIKE ?2 AND city LIKE ?3 AND sector LIKE ?4 ORDER BY name ASC", nativeQuery=true)
	public List<Company> findCompanyByAllOrderByNameAsc (String name,String address,String city,String sector);
	
	@Query(value = "SELECT DISTINCT sector FROM company ORDER BY sector ASC", nativeQuery = true)
	public List<String> getDistinctSectorOrderAsc();
}