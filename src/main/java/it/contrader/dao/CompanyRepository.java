package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;

import it.contrader.model.Company;
import it.contrader.model.User;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company,Integer> {
	public List<Company> findAllByAll(String name,String address,String city,String sector);
}