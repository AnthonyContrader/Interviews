package it.contrader.interviews.service.impl;

import it.contrader.interviews.service.RecruiterService;
import it.contrader.interviews.domain.Recruiter;
import it.contrader.interviews.repository.RecruiterRepository;
import it.contrader.interviews.service.dto.RecruiterDTO;
import it.contrader.interviews.service.mapper.RecruiterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Recruiter.
 */
@Service
@Transactional
public class RecruiterServiceImpl implements RecruiterService {

    private final Logger log = LoggerFactory.getLogger(RecruiterServiceImpl.class);

    private final RecruiterRepository recruiterRepository;

    private final RecruiterMapper recruiterMapper;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository, RecruiterMapper recruiterMapper) {
        this.recruiterRepository = recruiterRepository;
        this.recruiterMapper = recruiterMapper;
    }

    /**
     * Save a recruiter.
     *
     * @param recruiterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RecruiterDTO save(RecruiterDTO recruiterDTO) {
        log.debug("Request to save Recruiter : {}", recruiterDTO);
        Recruiter recruiter = recruiterMapper.toEntity(recruiterDTO);
        recruiter = recruiterRepository.save(recruiter);
        return recruiterMapper.toDto(recruiter);
    }

    /**
     * Get all the recruiters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecruiterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Recruiters");
        return recruiterRepository.findAll(pageable)
            .map(recruiterMapper::toDto);
    }


    /**
     * Get one recruiter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RecruiterDTO> findOne(Long id) {
        log.debug("Request to get Recruiter : {}", id);
        return recruiterRepository.findById(id)
            .map(recruiterMapper::toDto);
    }

    /**
     * Delete the recruiter by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recruiter : {}", id);
        recruiterRepository.deleteById(id);
    }
}
