package co.com.elpoli.web.rest;

import co.com.elpoli.TriviaApp;

import co.com.elpoli.domain.Expedicionuser;
import co.com.elpoli.domain.Detalleexpedicionuser;
import co.com.elpoli.domain.Expedicion;
import co.com.elpoli.repository.ExpedicionuserRepository;
import co.com.elpoli.service.ExpedicionuserService;
import co.com.elpoli.service.dto.ExpedicionuserDTO;
import co.com.elpoli.service.mapper.ExpedicionuserMapper;
import co.com.elpoli.web.rest.errors.ExceptionTranslator;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExpedicionuserResource REST controller.
 *
 * @see ExpedicionuserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TriviaApp.class)
public class ExpedicionuserResourceIntTest {

    private static final String DEFAULT_NICKNAME = "AAAAAAAAAA";
    private static final String UPDATED_NICKNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ExpedicionuserRepository expedicionuserRepository;

    @Autowired
    private ExpedicionuserMapper expedicionuserMapper;

    @Autowired
    private ExpedicionuserService expedicionuserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restExpedicionuserMockMvc;

    private Expedicionuser expedicionuser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExpedicionuserResource expedicionuserResource = new ExpedicionuserResource(expedicionuserService);
        this.restExpedicionuserMockMvc = MockMvcBuilders.standaloneSetup(expedicionuserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Expedicionuser createEntity(EntityManager em) {
        Expedicionuser expedicionuser = new Expedicionuser()
            .nickname(DEFAULT_NICKNAME)
            .fechaRegistro(DEFAULT_FECHA_REGISTRO);
        // Add required entity
        Detalleexpedicionuser detalleexpedicionuser = DetalleexpedicionuserResourceIntTest.createEntity(em);
        em.persist(detalleexpedicionuser);
        em.flush();
        expedicionuser.getDetalleexpedicionusers().add(detalleexpedicionuser);
        // Add required entity
        Expedicion expedicion = ExpedicionResourceIntTest.createEntity(em);
        em.persist(expedicion);
        em.flush();
        expedicionuser.setExpedicion(expedicion);
        return expedicionuser;
    }

    @Before
    public void initTest() {
        expedicionuser = createEntity(em);
    }

    @Test
    @Transactional
    public void createExpedicionuser() throws Exception {
        int databaseSizeBeforeCreate = expedicionuserRepository.findAll().size();

        // Create the Expedicionuser
        ExpedicionuserDTO expedicionuserDTO = expedicionuserMapper.toDto(expedicionuser);
        restExpedicionuserMockMvc.perform(post("/api/expedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(expedicionuserDTO)))
            .andExpect(status().isCreated());

        // Validate the Expedicionuser in the database
        List<Expedicionuser> expedicionuserList = expedicionuserRepository.findAll();
        assertThat(expedicionuserList).hasSize(databaseSizeBeforeCreate + 1);
        Expedicionuser testExpedicionuser = expedicionuserList.get(expedicionuserList.size() - 1);
        assertThat(testExpedicionuser.getNickname()).isEqualTo(DEFAULT_NICKNAME);
        assertThat(testExpedicionuser.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
    }

    @Test
    @Transactional
    public void createExpedicionuserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = expedicionuserRepository.findAll().size();

        // Create the Expedicionuser with an existing ID
        expedicionuser.setId(1L);
        ExpedicionuserDTO expedicionuserDTO = expedicionuserMapper.toDto(expedicionuser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExpedicionuserMockMvc.perform(post("/api/expedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(expedicionuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Expedicionuser in the database
        List<Expedicionuser> expedicionuserList = expedicionuserRepository.findAll();
        assertThat(expedicionuserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNicknameIsRequired() throws Exception {
        int databaseSizeBeforeTest = expedicionuserRepository.findAll().size();
        // set the field null
        expedicionuser.setNickname(null);

        // Create the Expedicionuser, which fails.
        ExpedicionuserDTO expedicionuserDTO = expedicionuserMapper.toDto(expedicionuser);

        restExpedicionuserMockMvc.perform(post("/api/expedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(expedicionuserDTO)))
            .andExpect(status().isBadRequest());

        List<Expedicionuser> expedicionuserList = expedicionuserRepository.findAll();
        assertThat(expedicionuserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaRegistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = expedicionuserRepository.findAll().size();
        // set the field null
        expedicionuser.setFechaRegistro(null);

        // Create the Expedicionuser, which fails.
        ExpedicionuserDTO expedicionuserDTO = expedicionuserMapper.toDto(expedicionuser);

        restExpedicionuserMockMvc.perform(post("/api/expedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(expedicionuserDTO)))
            .andExpect(status().isBadRequest());

        List<Expedicionuser> expedicionuserList = expedicionuserRepository.findAll();
        assertThat(expedicionuserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExpedicionusers() throws Exception {
        // Initialize the database
        expedicionuserRepository.saveAndFlush(expedicionuser);

        // Get all the expedicionuserList
        restExpedicionuserMockMvc.perform(get("/api/expedicionusers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(expedicionuser.getId().intValue())))
            .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME.toString())))
            .andExpect(jsonPath("$.[*].fechaRegistro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())));
    }

    @Test
    @Transactional
    public void getExpedicionuser() throws Exception {
        // Initialize the database
        expedicionuserRepository.saveAndFlush(expedicionuser);

        // Get the expedicionuser
        restExpedicionuserMockMvc.perform(get("/api/expedicionusers/{id}", expedicionuser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(expedicionuser.getId().intValue()))
            .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME.toString()))
            .andExpect(jsonPath("$.fechaRegistro").value(DEFAULT_FECHA_REGISTRO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExpedicionuser() throws Exception {
        // Get the expedicionuser
        restExpedicionuserMockMvc.perform(get("/api/expedicionusers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExpedicionuser() throws Exception {
        // Initialize the database
        expedicionuserRepository.saveAndFlush(expedicionuser);
        int databaseSizeBeforeUpdate = expedicionuserRepository.findAll().size();

        // Update the expedicionuser
        Expedicionuser updatedExpedicionuser = expedicionuserRepository.findOne(expedicionuser.getId());
        updatedExpedicionuser
            .nickname(UPDATED_NICKNAME)
            .fechaRegistro(UPDATED_FECHA_REGISTRO);
        ExpedicionuserDTO expedicionuserDTO = expedicionuserMapper.toDto(updatedExpedicionuser);

        restExpedicionuserMockMvc.perform(put("/api/expedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(expedicionuserDTO)))
            .andExpect(status().isOk());

        // Validate the Expedicionuser in the database
        List<Expedicionuser> expedicionuserList = expedicionuserRepository.findAll();
        assertThat(expedicionuserList).hasSize(databaseSizeBeforeUpdate);
        Expedicionuser testExpedicionuser = expedicionuserList.get(expedicionuserList.size() - 1);
        assertThat(testExpedicionuser.getNickname()).isEqualTo(UPDATED_NICKNAME);
        assertThat(testExpedicionuser.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
    }

    @Test
    @Transactional
    public void updateNonExistingExpedicionuser() throws Exception {
        int databaseSizeBeforeUpdate = expedicionuserRepository.findAll().size();

        // Create the Expedicionuser
        ExpedicionuserDTO expedicionuserDTO = expedicionuserMapper.toDto(expedicionuser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restExpedicionuserMockMvc.perform(put("/api/expedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(expedicionuserDTO)))
            .andExpect(status().isCreated());

        // Validate the Expedicionuser in the database
        List<Expedicionuser> expedicionuserList = expedicionuserRepository.findAll();
        assertThat(expedicionuserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteExpedicionuser() throws Exception {
        // Initialize the database
        expedicionuserRepository.saveAndFlush(expedicionuser);
        int databaseSizeBeforeDelete = expedicionuserRepository.findAll().size();

        // Get the expedicionuser
        restExpedicionuserMockMvc.perform(delete("/api/expedicionusers/{id}", expedicionuser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Expedicionuser> expedicionuserList = expedicionuserRepository.findAll();
        assertThat(expedicionuserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Expedicionuser.class);
        Expedicionuser expedicionuser1 = new Expedicionuser();
        expedicionuser1.setId(1L);
        Expedicionuser expedicionuser2 = new Expedicionuser();
        expedicionuser2.setId(expedicionuser1.getId());
        assertThat(expedicionuser1).isEqualTo(expedicionuser2);
        expedicionuser2.setId(2L);
        assertThat(expedicionuser1).isNotEqualTo(expedicionuser2);
        expedicionuser1.setId(null);
        assertThat(expedicionuser1).isNotEqualTo(expedicionuser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExpedicionuserDTO.class);
        ExpedicionuserDTO expedicionuserDTO1 = new ExpedicionuserDTO();
        expedicionuserDTO1.setId(1L);
        ExpedicionuserDTO expedicionuserDTO2 = new ExpedicionuserDTO();
        assertThat(expedicionuserDTO1).isNotEqualTo(expedicionuserDTO2);
        expedicionuserDTO2.setId(expedicionuserDTO1.getId());
        assertThat(expedicionuserDTO1).isEqualTo(expedicionuserDTO2);
        expedicionuserDTO2.setId(2L);
        assertThat(expedicionuserDTO1).isNotEqualTo(expedicionuserDTO2);
        expedicionuserDTO1.setId(null);
        assertThat(expedicionuserDTO1).isNotEqualTo(expedicionuserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(expedicionuserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(expedicionuserMapper.fromId(null)).isNull();
    }
}
