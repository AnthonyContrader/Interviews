package it.contrader.interviews.repository;

import it.contrader.interviews.domain.Recruiter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Recruiter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

}
