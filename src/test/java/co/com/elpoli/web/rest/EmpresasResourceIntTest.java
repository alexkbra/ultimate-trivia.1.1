package co.com.elpoli.web.rest;

import co.com.elpoli.TriviaApp;

import co.com.elpoli.domain.Empresas;
import co.com.elpoli.domain.Publicidad;
import co.com.elpoli.repository.EmpresasRepository;
import co.com.elpoli.service.EmpresasService;
import co.com.elpoli.service.dto.EmpresasDTO;
import co.com.elpoli.service.mapper.EmpresasMapper;
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
 * Test class for the EmpresasResource REST controller.
 *
 * @see EmpresasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TriviaApp.class)
public class EmpresasResourceIntTest {

    private static final String DEFAULT_RAZON_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAZON_SOCIAL = "BBBBBBBBBB";

    private static final Long DEFAULT_NIT = 9L;
    private static final Long UPDATED_NIT = 10L;

    private static final Integer DEFAULT_DIGITO_VERIFICACION = 1;
    private static final Integer UPDATED_DIGITO_VERIFICACION = 2;

    private static final Long DEFAULT_TELEFONO = 7L;
    private static final Long UPDATED_TELEFONO = 8L;

    private static final Long DEFAULT_TELEFONO_CONTACTO = 7L;
    private static final Long UPDATED_TELEFONO_CONTACTO = 8L;

    private static final String DEFAULT_EMAIL = "3@c7.dvt";
    private static final String UPDATED_EMAIL = "f@o.rrly";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_PAGINA_WEB = "AAAAAAAAAA";
    private static final String UPDATED_PAGINA_WEB = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private EmpresasRepository empresasRepository;

    @Autowired
    private EmpresasMapper empresasMapper;

    @Autowired
    private EmpresasService empresasService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmpresasMockMvc;

    private Empresas empresas;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmpresasResource empresasResource = new EmpresasResource(empresasService);
        this.restEmpresasMockMvc = MockMvcBuilders.standaloneSetup(empresasResource)
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
    public static Empresas createEntity(EntityManager em) {
        Empresas empresas = new Empresas()
            .razonSocial(DEFAULT_RAZON_SOCIAL)
            .nit(DEFAULT_NIT)
            .digitoVerificacion(DEFAULT_DIGITO_VERIFICACION)
            .telefono(DEFAULT_TELEFONO)
            .telefonoContacto(DEFAULT_TELEFONO_CONTACTO)
            .email(DEFAULT_EMAIL)
            .direccion(DEFAULT_DIRECCION)
            .paginaWeb(DEFAULT_PAGINA_WEB)
            .fechaRegistro(DEFAULT_FECHA_REGISTRO);
        // Add required entity
        Publicidad publicidad = PublicidadResourceIntTest.createEntity(em);
        em.persist(publicidad);
        em.flush();
        empresas.getPublicidads().add(publicidad);
        return empresas;
    }

    @Before
    public void initTest() {
        empresas = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmpresas() throws Exception {
        int databaseSizeBeforeCreate = empresasRepository.findAll().size();

        // Create the Empresas
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);
        restEmpresasMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isCreated());

        // Validate the Empresas in the database
        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeCreate + 1);
        Empresas testEmpresas = empresasList.get(empresasList.size() - 1);
        assertThat(testEmpresas.getRazonSocial()).isEqualTo(DEFAULT_RAZON_SOCIAL);
        assertThat(testEmpresas.getNit()).isEqualTo(DEFAULT_NIT);
        assertThat(testEmpresas.getDigitoVerificacion()).isEqualTo(DEFAULT_DIGITO_VERIFICACION);
        assertThat(testEmpresas.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testEmpresas.getTelefonoContacto()).isEqualTo(DEFAULT_TELEFONO_CONTACTO);
        assertThat(testEmpresas.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEmpresas.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testEmpresas.getPaginaWeb()).isEqualTo(DEFAULT_PAGINA_WEB);
        assertThat(testEmpresas.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
    }

    @Test
    @Transactional
    public void createEmpresasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = empresasRepository.findAll().size();

        // Create the Empresas with an existing ID
        empresas.setId(1L);
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpresasMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Empresas in the database
        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRazonSocialIsRequired() throws Exception {
        int databaseSizeBeforeTest = empresasRepository.findAll().size();
        // set the field null
        empresas.setRazonSocial(null);

        // Create the Empresas, which fails.
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);

        restEmpresasMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isBadRequest());

        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNitIsRequired() throws Exception {
        int databaseSizeBeforeTest = empresasRepository.findAll().size();
        // set the field null
        empresas.setNit(null);

        // Create the Empresas, which fails.
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);

        restEmpresasMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isBadRequest());

        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDigitoVerificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = empresasRepository.findAll().size();
        // set the field null
        empresas.setDigitoVerificacion(null);

        // Create the Empresas, which fails.
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);

        restEmpresasMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isBadRequest());

        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefonoIsRequired() throws Exception {
        int databaseSizeBeforeTest = empresasRepository.findAll().size();
        // set the field null
        empresas.setTelefono(null);

        // Create the Empresas, which fails.
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);

        restEmpresasMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isBadRequest());

        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaRegistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = empresasRepository.findAll().size();
        // set the field null
        empresas.setFechaRegistro(null);

        // Create the Empresas, which fails.
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);

        restEmpresasMockMvc.perform(post("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isBadRequest());

        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmpresas() throws Exception {
        // Initialize the database
        empresasRepository.saveAndFlush(empresas);

        // Get all the empresasList
        restEmpresasMockMvc.perform(get("/api/empresas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empresas.getId().intValue())))
            .andExpect(jsonPath("$.[*].razonSocial").value(hasItem(DEFAULT_RAZON_SOCIAL.toString())))
            .andExpect(jsonPath("$.[*].nit").value(hasItem(DEFAULT_NIT.intValue())))
            .andExpect(jsonPath("$.[*].digitoVerificacion").value(hasItem(DEFAULT_DIGITO_VERIFICACION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.intValue())))
            .andExpect(jsonPath("$.[*].telefonoContacto").value(hasItem(DEFAULT_TELEFONO_CONTACTO.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].paginaWeb").value(hasItem(DEFAULT_PAGINA_WEB.toString())))
            .andExpect(jsonPath("$.[*].fechaRegistro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())));
    }

    @Test
    @Transactional
    public void getEmpresas() throws Exception {
        // Initialize the database
        empresasRepository.saveAndFlush(empresas);

        // Get the empresas
        restEmpresasMockMvc.perform(get("/api/empresas/{id}", empresas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(empresas.getId().intValue()))
            .andExpect(jsonPath("$.razonSocial").value(DEFAULT_RAZON_SOCIAL.toString()))
            .andExpect(jsonPath("$.nit").value(DEFAULT_NIT.intValue()))
            .andExpect(jsonPath("$.digitoVerificacion").value(DEFAULT_DIGITO_VERIFICACION))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.intValue()))
            .andExpect(jsonPath("$.telefonoContacto").value(DEFAULT_TELEFONO_CONTACTO.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.paginaWeb").value(DEFAULT_PAGINA_WEB.toString()))
            .andExpect(jsonPath("$.fechaRegistro").value(DEFAULT_FECHA_REGISTRO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmpresas() throws Exception {
        // Get the empresas
        restEmpresasMockMvc.perform(get("/api/empresas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpresas() throws Exception {
        // Initialize the database
        empresasRepository.saveAndFlush(empresas);
        int databaseSizeBeforeUpdate = empresasRepository.findAll().size();

        // Update the empresas
        Empresas updatedEmpresas = empresasRepository.findOne(empresas.getId());
        updatedEmpresas
            .razonSocial(UPDATED_RAZON_SOCIAL)
            .nit(UPDATED_NIT)
            .digitoVerificacion(UPDATED_DIGITO_VERIFICACION)
            .telefono(UPDATED_TELEFONO)
            .telefonoContacto(UPDATED_TELEFONO_CONTACTO)
            .email(UPDATED_EMAIL)
            .direccion(UPDATED_DIRECCION)
            .paginaWeb(UPDATED_PAGINA_WEB)
            .fechaRegistro(UPDATED_FECHA_REGISTRO);
        EmpresasDTO empresasDTO = empresasMapper.toDto(updatedEmpresas);

        restEmpresasMockMvc.perform(put("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isOk());

        // Validate the Empresas in the database
        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeUpdate);
        Empresas testEmpresas = empresasList.get(empresasList.size() - 1);
        assertThat(testEmpresas.getRazonSocial()).isEqualTo(UPDATED_RAZON_SOCIAL);
        assertThat(testEmpresas.getNit()).isEqualTo(UPDATED_NIT);
        assertThat(testEmpresas.getDigitoVerificacion()).isEqualTo(UPDATED_DIGITO_VERIFICACION);
        assertThat(testEmpresas.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testEmpresas.getTelefonoContacto()).isEqualTo(UPDATED_TELEFONO_CONTACTO);
        assertThat(testEmpresas.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEmpresas.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEmpresas.getPaginaWeb()).isEqualTo(UPDATED_PAGINA_WEB);
        assertThat(testEmpresas.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
    }

    @Test
    @Transactional
    public void updateNonExistingEmpresas() throws Exception {
        int databaseSizeBeforeUpdate = empresasRepository.findAll().size();

        // Create the Empresas
        EmpresasDTO empresasDTO = empresasMapper.toDto(empresas);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmpresasMockMvc.perform(put("/api/empresas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empresasDTO)))
            .andExpect(status().isCreated());

        // Validate the Empresas in the database
        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEmpresas() throws Exception {
        // Initialize the database
        empresasRepository.saveAndFlush(empresas);
        int databaseSizeBeforeDelete = empresasRepository.findAll().size();

        // Get the empresas
        restEmpresasMockMvc.perform(delete("/api/empresas/{id}", empresas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Empresas> empresasList = empresasRepository.findAll();
        assertThat(empresasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empresas.class);
        Empresas empresas1 = new Empresas();
        empresas1.setId(1L);
        Empresas empresas2 = new Empresas();
        empresas2.setId(empresas1.getId());
        assertThat(empresas1).isEqualTo(empresas2);
        empresas2.setId(2L);
        assertThat(empresas1).isNotEqualTo(empresas2);
        empresas1.setId(null);
        assertThat(empresas1).isNotEqualTo(empresas2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpresasDTO.class);
        EmpresasDTO empresasDTO1 = new EmpresasDTO();
        empresasDTO1.setId(1L);
        EmpresasDTO empresasDTO2 = new EmpresasDTO();
        assertThat(empresasDTO1).isNotEqualTo(empresasDTO2);
        empresasDTO2.setId(empresasDTO1.getId());
        assertThat(empresasDTO1).isEqualTo(empresasDTO2);
        empresasDTO2.setId(2L);
        assertThat(empresasDTO1).isNotEqualTo(empresasDTO2);
        empresasDTO1.setId(null);
        assertThat(empresasDTO1).isNotEqualTo(empresasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(empresasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(empresasMapper.fromId(null)).isNull();
    }
}
