package it.contrader.interviews.web.rest;

import it.contrader.interviews.Interviews1App;

import it.contrader.interviews.domain.Recruiter;
import it.contrader.interviews.repository.RecruiterRepository;
import it.contrader.interviews.service.RecruiterService;
import it.contrader.interviews.service.dto.RecruiterDTO;
import it.contrader.interviews.service.mapper.RecruiterMapper;
import it.contrader.interviews.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static it.contrader.interviews.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RecruiterResource REST controller.
 *
 * @see RecruiterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Interviews1App.class)
public class RecruiterResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    @Autowired
    private RecruiterRepository recruiterRepository;


    @Autowired
    private RecruiterMapper recruiterMapper;
    

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRecruiterMockMvc;

    private Recruiter recruiter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecruiterResource recruiterResource = new RecruiterResource(recruiterService);
        this.restRecruiterMockMvc = MockMvcBuilders.standaloneSetup(recruiterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Recruiter createEntity(EntityManager em) {
        Recruiter recruiter = new Recruiter()
            .name(DEFAULT_NAME)
            .companyId(DEFAULT_COMPANY_ID)
            .companyName(DEFAULT_COMPANY_NAME);
        return recruiter;
    }

    @Before
    public void initTest() {
        recruiter = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecruiter() throws Exception {
        int databaseSizeBeforeCreate = recruiterRepository.findAll().size();

        // Create the Recruiter
        RecruiterDTO recruiterDTO = recruiterMapper.toDto(recruiter);
        restRecruiterMockMvc.perform(post("/api/recruiters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruiterDTO)))
            .andExpect(status().isCreated());

        // Validate the Recruiter in the database
        List<Recruiter> recruiterList = recruiterRepository.findAll();
        assertThat(recruiterList).hasSize(databaseSizeBeforeCreate + 1);
        Recruiter testRecruiter = recruiterList.get(recruiterList.size() - 1);
        assertThat(testRecruiter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRecruiter.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testRecruiter.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void createRecruiterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recruiterRepository.findAll().size();

        // Create the Recruiter with an existing ID
        recruiter.setId(1L);
        RecruiterDTO recruiterDTO = recruiterMapper.toDto(recruiter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecruiterMockMvc.perform(post("/api/recruiters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruiterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recruiter in the database
        List<Recruiter> recruiterList = recruiterRepository.findAll();
        assertThat(recruiterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = recruiterRepository.findAll().size();
        // set the field null
        recruiter.setName(null);

        // Create the Recruiter, which fails.
        RecruiterDTO recruiterDTO = recruiterMapper.toDto(recruiter);

        restRecruiterMockMvc.perform(post("/api/recruiters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruiterDTO)))
            .andExpect(status().isBadRequest());

        List<Recruiter> recruiterList = recruiterRepository.findAll();
        assertThat(recruiterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompanyIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = recruiterRepository.findAll().size();
        // set the field null
        recruiter.setCompanyId(null);

        // Create the Recruiter, which fails.
        RecruiterDTO recruiterDTO = recruiterMapper.toDto(recruiter);

        restRecruiterMockMvc.perform(post("/api/recruiters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruiterDTO)))
            .andExpect(status().isBadRequest());

        List<Recruiter> recruiterList = recruiterRepository.findAll();
        assertThat(recruiterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRecruiters() throws Exception {
        // Initialize the database
        recruiterRepository.saveAndFlush(recruiter);

        // Get all the recruiterList
        restRecruiterMockMvc.perform(get("/api/recruiters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recruiter.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getRecruiter() throws Exception {
        // Initialize the database
        recruiterRepository.saveAndFlush(recruiter);

        // Get the recruiter
        restRecruiterMockMvc.perform(get("/api/recruiters/{id}", recruiter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recruiter.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRecruiter() throws Exception {
        // Get the recruiter
        restRecruiterMockMvc.perform(get("/api/recruiters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecruiter() throws Exception {
        // Initialize the database
        recruiterRepository.saveAndFlush(recruiter);

        int databaseSizeBeforeUpdate = recruiterRepository.findAll().size();

        // Update the recruiter
        Recruiter updatedRecruiter = recruiterRepository.findById(recruiter.getId()).get();
        // Disconnect from session so that the updates on updatedRecruiter are not directly saved in db
        em.detach(updatedRecruiter);
        updatedRecruiter
            .name(UPDATED_NAME)
            .companyId(UPDATED_COMPANY_ID)
            .companyName(UPDATED_COMPANY_NAME);
        RecruiterDTO recruiterDTO = recruiterMapper.toDto(updatedRecruiter);

        restRecruiterMockMvc.perform(put("/api/recruiters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruiterDTO)))
            .andExpect(status().isOk());

        // Validate the Recruiter in the database
        List<Recruiter> recruiterList = recruiterRepository.findAll();
        assertThat(recruiterList).hasSize(databaseSizeBeforeUpdate);
        Recruiter testRecruiter = recruiterList.get(recruiterList.size() - 1);
        assertThat(testRecruiter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRecruiter.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testRecruiter.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingRecruiter() throws Exception {
        int databaseSizeBeforeUpdate = recruiterRepository.findAll().size();

        // Create the Recruiter
        RecruiterDTO recruiterDTO = recruiterMapper.toDto(recruiter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restRecruiterMockMvc.perform(put("/api/recruiters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recruiterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Recruiter in the database
        List<Recruiter> recruiterList = recruiterRepository.findAll();
        assertThat(recruiterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecruiter() throws Exception {
        // Initialize the database
        recruiterRepository.saveAndFlush(recruiter);

        int databaseSizeBeforeDelete = recruiterRepository.findAll().size();

        // Get the recruiter
        restRecruiterMockMvc.perform(delete("/api/recruiters/{id}", recruiter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Recruiter> recruiterList = recruiterRepository.findAll();
        assertThat(recruiterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recruiter.class);
        Recruiter recruiter1 = new Recruiter();
        recruiter1.setId(1L);
        Recruiter recruiter2 = new Recruiter();
        recruiter2.setId(recruiter1.getId());
        assertThat(recruiter1).isEqualTo(recruiter2);
        recruiter2.setId(2L);
        assertThat(recruiter1).isNotEqualTo(recruiter2);
        recruiter1.setId(null);
        assertThat(recruiter1).isNotEqualTo(recruiter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecruiterDTO.class);
        RecruiterDTO recruiterDTO1 = new RecruiterDTO();
        recruiterDTO1.setId(1L);
        RecruiterDTO recruiterDTO2 = new RecruiterDTO();
        assertThat(recruiterDTO1).isNotEqualTo(recruiterDTO2);
        recruiterDTO2.setId(recruiterDTO1.getId());
        assertThat(recruiterDTO1).isEqualTo(recruiterDTO2);
        recruiterDTO2.setId(2L);
        assertThat(recruiterDTO1).isNotEqualTo(recruiterDTO2);
        recruiterDTO1.setId(null);
        assertThat(recruiterDTO1).isNotEqualTo(recruiterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(recruiterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(recruiterMapper.fromId(null)).isNull();
    }
}
