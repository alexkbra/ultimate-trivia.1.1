package co.com.elpoli.web.rest;

import co.com.elpoli.TriviaApp;

import co.com.elpoli.domain.Detalleexpedicionuser;
import co.com.elpoli.domain.Pregunta;
import co.com.elpoli.domain.Expedicionuser;
import co.com.elpoli.repository.DetalleexpedicionuserRepository;
import co.com.elpoli.service.DetalleexpedicionuserService;
import co.com.elpoli.service.dto.DetalleexpedicionuserDTO;
import co.com.elpoli.service.mapper.DetalleexpedicionuserMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DetalleexpedicionuserResource REST controller.
 *
 * @see DetalleexpedicionuserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TriviaApp.class)
public class DetalleexpedicionuserResourceIntTest {

    private static final Instant DEFAULT_FECHA_RESPUETA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_RESPUETA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_RESPUESTA = false;
    private static final Boolean UPDATED_RESPUESTA = true;

    @Autowired
    private DetalleexpedicionuserRepository detalleexpedicionuserRepository;

    @Autowired
    private DetalleexpedicionuserMapper detalleexpedicionuserMapper;

    @Autowired
    private DetalleexpedicionuserService detalleexpedicionuserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDetalleexpedicionuserMockMvc;

    private Detalleexpedicionuser detalleexpedicionuser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DetalleexpedicionuserResource detalleexpedicionuserResource = new DetalleexpedicionuserResource(detalleexpedicionuserService);
        this.restDetalleexpedicionuserMockMvc = MockMvcBuilders.standaloneSetup(detalleexpedicionuserResource)
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
    public static Detalleexpedicionuser createEntity(EntityManager em) {
        Detalleexpedicionuser detalleexpedicionuser = new Detalleexpedicionuser()
            .fechaRespueta(DEFAULT_FECHA_RESPUETA)
            .respuesta(DEFAULT_RESPUESTA);
        // Add required entity
        Pregunta pregunta = PreguntaResourceIntTest.createEntity(em);
        em.persist(pregunta);
        em.flush();
        detalleexpedicionuser.setPregunta(pregunta);
        // Add required entity
        Expedicionuser expedicionuser = ExpedicionuserResourceIntTest.createEntity(em);
        em.persist(expedicionuser);
        em.flush();
        detalleexpedicionuser.setExpedicionuser(expedicionuser);
        return detalleexpedicionuser;
    }

    @Before
    public void initTest() {
        detalleexpedicionuser = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetalleexpedicionuser() throws Exception {
        int databaseSizeBeforeCreate = detalleexpedicionuserRepository.findAll().size();

        // Create the Detalleexpedicionuser
        DetalleexpedicionuserDTO detalleexpedicionuserDTO = detalleexpedicionuserMapper.toDto(detalleexpedicionuser);
        restDetalleexpedicionuserMockMvc.perform(post("/api/detalleexpedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleexpedicionuserDTO)))
            .andExpect(status().isCreated());

        // Validate the Detalleexpedicionuser in the database
        List<Detalleexpedicionuser> detalleexpedicionuserList = detalleexpedicionuserRepository.findAll();
        assertThat(detalleexpedicionuserList).hasSize(databaseSizeBeforeCreate + 1);
        Detalleexpedicionuser testDetalleexpedicionuser = detalleexpedicionuserList.get(detalleexpedicionuserList.size() - 1);
        assertThat(testDetalleexpedicionuser.getFechaRespueta()).isEqualTo(DEFAULT_FECHA_RESPUETA);
        assertThat(testDetalleexpedicionuser.isRespuesta()).isEqualTo(DEFAULT_RESPUESTA);
    }

    @Test
    @Transactional
    public void createDetalleexpedicionuserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detalleexpedicionuserRepository.findAll().size();

        // Create the Detalleexpedicionuser with an existing ID
        detalleexpedicionuser.setId(1L);
        DetalleexpedicionuserDTO detalleexpedicionuserDTO = detalleexpedicionuserMapper.toDto(detalleexpedicionuser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetalleexpedicionuserMockMvc.perform(post("/api/detalleexpedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleexpedicionuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Detalleexpedicionuser in the database
        List<Detalleexpedicionuser> detalleexpedicionuserList = detalleexpedicionuserRepository.findAll();
        assertThat(detalleexpedicionuserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFechaRespuetaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleexpedicionuserRepository.findAll().size();
        // set the field null
        detalleexpedicionuser.setFechaRespueta(null);

        // Create the Detalleexpedicionuser, which fails.
        DetalleexpedicionuserDTO detalleexpedicionuserDTO = detalleexpedicionuserMapper.toDto(detalleexpedicionuser);

        restDetalleexpedicionuserMockMvc.perform(post("/api/detalleexpedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleexpedicionuserDTO)))
            .andExpect(status().isBadRequest());

        List<Detalleexpedicionuser> detalleexpedicionuserList = detalleexpedicionuserRepository.findAll();
        assertThat(detalleexpedicionuserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRespuestaIsRequired() throws Exception {
        int databaseSizeBeforeTest = detalleexpedicionuserRepository.findAll().size();
        // set the field null
        detalleexpedicionuser.setRespuesta(null);

        // Create the Detalleexpedicionuser, which fails.
        DetalleexpedicionuserDTO detalleexpedicionuserDTO = detalleexpedicionuserMapper.toDto(detalleexpedicionuser);

        restDetalleexpedicionuserMockMvc.perform(post("/api/detalleexpedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleexpedicionuserDTO)))
            .andExpect(status().isBadRequest());

        List<Detalleexpedicionuser> detalleexpedicionuserList = detalleexpedicionuserRepository.findAll();
        assertThat(detalleexpedicionuserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDetalleexpedicionusers() throws Exception {
        // Initialize the database
        detalleexpedicionuserRepository.saveAndFlush(detalleexpedicionuser);

        // Get all the detalleexpedicionuserList
        restDetalleexpedicionuserMockMvc.perform(get("/api/detalleexpedicionusers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detalleexpedicionuser.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaRespueta").value(hasItem(DEFAULT_FECHA_RESPUETA.toString())))
            .andExpect(jsonPath("$.[*].respuesta").value(hasItem(DEFAULT_RESPUESTA.booleanValue())));
    }

    @Test
    @Transactional
    public void getDetalleexpedicionuser() throws Exception {
        // Initialize the database
        detalleexpedicionuserRepository.saveAndFlush(detalleexpedicionuser);

        // Get the detalleexpedicionuser
        restDetalleexpedicionuserMockMvc.perform(get("/api/detalleexpedicionusers/{id}", detalleexpedicionuser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(detalleexpedicionuser.getId().intValue()))
            .andExpect(jsonPath("$.fechaRespueta").value(DEFAULT_FECHA_RESPUETA.toString()))
            .andExpect(jsonPath("$.respuesta").value(DEFAULT_RESPUESTA.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDetalleexpedicionuser() throws Exception {
        // Get the detalleexpedicionuser
        restDetalleexpedicionuserMockMvc.perform(get("/api/detalleexpedicionusers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetalleexpedicionuser() throws Exception {
        // Initialize the database
        detalleexpedicionuserRepository.saveAndFlush(detalleexpedicionuser);
        int databaseSizeBeforeUpdate = detalleexpedicionuserRepository.findAll().size();

        // Update the detalleexpedicionuser
        Detalleexpedicionuser updatedDetalleexpedicionuser = detalleexpedicionuserRepository.findOne(detalleexpedicionuser.getId());
        updatedDetalleexpedicionuser
            .fechaRespueta(UPDATED_FECHA_RESPUETA)
            .respuesta(UPDATED_RESPUESTA);
        DetalleexpedicionuserDTO detalleexpedicionuserDTO = detalleexpedicionuserMapper.toDto(updatedDetalleexpedicionuser);

        restDetalleexpedicionuserMockMvc.perform(put("/api/detalleexpedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleexpedicionuserDTO)))
            .andExpect(status().isOk());

        // Validate the Detalleexpedicionuser in the database
        List<Detalleexpedicionuser> detalleexpedicionuserList = detalleexpedicionuserRepository.findAll();
        assertThat(detalleexpedicionuserList).hasSize(databaseSizeBeforeUpdate);
        Detalleexpedicionuser testDetalleexpedicionuser = detalleexpedicionuserList.get(detalleexpedicionuserList.size() - 1);
        assertThat(testDetalleexpedicionuser.getFechaRespueta()).isEqualTo(UPDATED_FECHA_RESPUETA);
        assertThat(testDetalleexpedicionuser.isRespuesta()).isEqualTo(UPDATED_RESPUESTA);
    }

    @Test
    @Transactional
    public void updateNonExistingDetalleexpedicionuser() throws Exception {
        int databaseSizeBeforeUpdate = detalleexpedicionuserRepository.findAll().size();

        // Create the Detalleexpedicionuser
        DetalleexpedicionuserDTO detalleexpedicionuserDTO = detalleexpedicionuserMapper.toDto(detalleexpedicionuser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDetalleexpedicionuserMockMvc.perform(put("/api/detalleexpedicionusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleexpedicionuserDTO)))
            .andExpect(status().isCreated());

        // Validate the Detalleexpedicionuser in the database
        List<Detalleexpedicionuser> detalleexpedicionuserList = detalleexpedicionuserRepository.findAll();
        assertThat(detalleexpedicionuserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDetalleexpedicionuser() throws Exception {
        // Initialize the database
        detalleexpedicionuserRepository.saveAndFlush(detalleexpedicionuser);
        int databaseSizeBeforeDelete = detalleexpedicionuserRepository.findAll().size();

        // Get the detalleexpedicionuser
        restDetalleexpedicionuserMockMvc.perform(delete("/api/detalleexpedicionusers/{id}", detalleexpedicionuser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Detalleexpedicionuser> detalleexpedicionuserList = detalleexpedicionuserRepository.findAll();
        assertThat(detalleexpedicionuserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Detalleexpedicionuser.class);
        Detalleexpedicionuser detalleexpedicionuser1 = new Detalleexpedicionuser();
        detalleexpedicionuser1.setId(1L);
        Detalleexpedicionuser detalleexpedicionuser2 = new Detalleexpedicionuser();
        detalleexpedicionuser2.setId(detalleexpedicionuser1.getId());
        assertThat(detalleexpedicionuser1).isEqualTo(detalleexpedicionuser2);
        detalleexpedicionuser2.setId(2L);
        assertThat(detalleexpedicionuser1).isNotEqualTo(detalleexpedicionuser2);
        detalleexpedicionuser1.setId(null);
        assertThat(detalleexpedicionuser1).isNotEqualTo(detalleexpedicionuser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetalleexpedicionuserDTO.class);
        DetalleexpedicionuserDTO detalleexpedicionuserDTO1 = new DetalleexpedicionuserDTO();
        detalleexpedicionuserDTO1.setId(1L);
        DetalleexpedicionuserDTO detalleexpedicionuserDTO2 = new DetalleexpedicionuserDTO();
        assertThat(detalleexpedicionuserDTO1).isNotEqualTo(detalleexpedicionuserDTO2);
        detalleexpedicionuserDTO2.setId(detalleexpedicionuserDTO1.getId());
        assertThat(detalleexpedicionuserDTO1).isEqualTo(detalleexpedicionuserDTO2);
        detalleexpedicionuserDTO2.setId(2L);
        assertThat(detalleexpedicionuserDTO1).isNotEqualTo(detalleexpedicionuserDTO2);
        detalleexpedicionuserDTO1.setId(null);
        assertThat(detalleexpedicionuserDTO1).isNotEqualTo(detalleexpedicionuserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(detalleexpedicionuserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(detalleexpedicionuserMapper.fromId(null)).isNull();
    }
}
