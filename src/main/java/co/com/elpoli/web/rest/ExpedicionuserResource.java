package co.com.elpoli.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.com.elpoli.service.ExpedicionuserService;
import co.com.elpoli.web.rest.util.HeaderUtil;
import co.com.elpoli.web.rest.util.PaginationUtil;
import co.com.elpoli.service.dto.ExpedicionuserDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Expedicionuser.
 */
@RestController
@RequestMapping("/api")
public class ExpedicionuserResource {

    private final Logger log = LoggerFactory.getLogger(ExpedicionuserResource.class);

    private static final String ENTITY_NAME = "expedicionuser";

    private final ExpedicionuserService expedicionuserService;

    public ExpedicionuserResource(ExpedicionuserService expedicionuserService) {
        this.expedicionuserService = expedicionuserService;
    }

    /**
     * POST  /expedicionusers : Create a new expedicionuser.
     *
     * @param expedicionuserDTO the expedicionuserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new expedicionuserDTO, or with status 400 (Bad Request) if the expedicionuser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/expedicionusers")
    @Timed
    public ResponseEntity<ExpedicionuserDTO> createExpedicionuser(@Valid @RequestBody ExpedicionuserDTO expedicionuserDTO) throws URISyntaxException {
        log.debug("REST request to save Expedicionuser : {}", expedicionuserDTO);
        if (expedicionuserDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new expedicionuser cannot already have an ID")).body(null);
        }
        ExpedicionuserDTO result = expedicionuserService.save(expedicionuserDTO);
        return ResponseEntity.created(new URI("/api/expedicionusers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /expedicionusers : Updates an existing expedicionuser.
     *
     * @param expedicionuserDTO the expedicionuserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated expedicionuserDTO,
     * or with status 400 (Bad Request) if the expedicionuserDTO is not valid,
     * or with status 500 (Internal Server Error) if the expedicionuserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/expedicionusers")
    @Timed
    public ResponseEntity<ExpedicionuserDTO> updateExpedicionuser(@Valid @RequestBody ExpedicionuserDTO expedicionuserDTO) throws URISyntaxException {
        log.debug("REST request to update Expedicionuser : {}", expedicionuserDTO);
        if (expedicionuserDTO.getId() == null) {
            return createExpedicionuser(expedicionuserDTO);
        }
        ExpedicionuserDTO result = expedicionuserService.save(expedicionuserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, expedicionuserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /expedicionusers : get all the expedicionusers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of expedicionusers in body
     */
    @GetMapping("/expedicionusers")
    @Timed
    public ResponseEntity<List<ExpedicionuserDTO>> getAllExpedicionusers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Expedicionusers");
        Page<ExpedicionuserDTO> page = expedicionuserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/expedicionusers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /expedicionusers/:id : get the "id" expedicionuser.
     *
     * @param id the id of the expedicionuserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the expedicionuserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/expedicionusers/{id}")
    @Timed
    public ResponseEntity<ExpedicionuserDTO> getExpedicionuser(@PathVariable Long id) {
        log.debug("REST request to get Expedicionuser : {}", id);
        ExpedicionuserDTO expedicionuserDTO = expedicionuserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(expedicionuserDTO));
    }

    /**
     * DELETE  /expedicionusers/:id : delete the "id" expedicionuser.
     *
     * @param id the id of the expedicionuserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/expedicionusers/{id}")
    @Timed
    public ResponseEntity<Void> deleteExpedicionuser(@PathVariable Long id) {
        log.debug("REST request to delete Expedicionuser : {}", id);
        expedicionuserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
