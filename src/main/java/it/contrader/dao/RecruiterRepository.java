package it.contrader.dao;

import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Recruiter;
import java.util.List;

public interface RecruiterRepository extends CrudRepository<Recruiter,Integer> {
	public List<Recruiter> findByNameAndCompanyId (String name, Integer companyId);
}
