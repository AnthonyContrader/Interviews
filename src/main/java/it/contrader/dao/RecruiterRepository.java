package it.contrader.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.contrader.model.Recruiter;
import java.util.List;

public interface RecruiterRepository extends CrudRepository<Recruiter,Integer> {
	@Query(value = "SELECT * FROM recruiter AS r LEFT JOIN company AS c ON r.company_Id=c.id WHERE r.name LIKE ?1 AND c.id LIKE ?2 ORDER BY r.name ASC",
			nativeQuery = true)
	public List<Recruiter> findRecruiterByAllOrderByNameAsc (String name, String companyId);
}
