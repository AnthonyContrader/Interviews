package it.contrader.interviews.service;

import it.contrader.interviews.service.dto.RecruiterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Recruiter.
 */
public interface RecruiterService {

    /**
     * Save a recruiter.
     *
     * @param recruiterDTO the entity to save
     * @return the persisted entity
     */
    RecruiterDTO save(RecruiterDTO recruiterDTO);

    /**
     * Get all the recruiters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RecruiterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" recruiter.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RecruiterDTO> findOne(Long id);

    /**
     * Delete the "id" recruiter.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
