package co.com.elpoli.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.com.elpoli.service.DetalleexpedicionuserService;
import co.com.elpoli.web.rest.util.HeaderUtil;
import co.com.elpoli.web.rest.util.PaginationUtil;
import co.com.elpoli.service.dto.DetalleexpedicionuserDTO;
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
 * REST controller for managing Detalleexpedicionuser.
 */
@RestController
@RequestMapping("/api")
public class DetalleexpedicionuserResource {

    private final Logger log = LoggerFactory.getLogger(DetalleexpedicionuserResource.class);

    private static final String ENTITY_NAME = "detalleexpedicionuser";

    private final DetalleexpedicionuserService detalleexpedicionuserService;

    public DetalleexpedicionuserResource(DetalleexpedicionuserService detalleexpedicionuserService) {
        this.detalleexpedicionuserService = detalleexpedicionuserService;
    }

    /**
     * POST  /detalleexpedicionusers : Create a new detalleexpedicionuser.
     *
     * @param detalleexpedicionuserDTO the detalleexpedicionuserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new detalleexpedicionuserDTO, or with status 400 (Bad Request) if the detalleexpedicionuser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/detalleexpedicionusers")
    @Timed
    public ResponseEntity<DetalleexpedicionuserDTO> createDetalleexpedicionuser(@Valid @RequestBody DetalleexpedicionuserDTO detalleexpedicionuserDTO) throws URISyntaxException {
        log.debug("REST request to save Detalleexpedicionuser : {}", detalleexpedicionuserDTO);
        if (detalleexpedicionuserDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new detalleexpedicionuser cannot already have an ID")).body(null);
        }
        DetalleexpedicionuserDTO result = detalleexpedicionuserService.save(detalleexpedicionuserDTO);
        return ResponseEntity.created(new URI("/api/detalleexpedicionusers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /detalleexpedicionusers : Updates an existing detalleexpedicionuser.
     *
     * @param detalleexpedicionuserDTO the detalleexpedicionuserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated detalleexpedicionuserDTO,
     * or with status 400 (Bad Request) if the detalleexpedicionuserDTO is not valid,
     * or with status 500 (Internal Server Error) if the detalleexpedicionuserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/detalleexpedicionusers")
    @Timed
    public ResponseEntity<DetalleexpedicionuserDTO> updateDetalleexpedicionuser(@Valid @RequestBody DetalleexpedicionuserDTO detalleexpedicionuserDTO) throws URISyntaxException {
        log.debug("REST request to update Detalleexpedicionuser : {}", detalleexpedicionuserDTO);
        if (detalleexpedicionuserDTO.getId() == null) {
            return createDetalleexpedicionuser(detalleexpedicionuserDTO);
        }
        DetalleexpedicionuserDTO result = detalleexpedicionuserService.save(detalleexpedicionuserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, detalleexpedicionuserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /detalleexpedicionusers : get all the detalleexpedicionusers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of detalleexpedicionusers in body
     */
    @GetMapping("/detalleexpedicionusers")
    @Timed
    public ResponseEntity<List<DetalleexpedicionuserDTO>> getAllDetalleexpedicionusers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Detalleexpedicionusers");
        Page<DetalleexpedicionuserDTO> page = detalleexpedicionuserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/detalleexpedicionusers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /detalleexpedicionusers/:id : get the "id" detalleexpedicionuser.
     *
     * @param id the id of the detalleexpedicionuserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the detalleexpedicionuserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/detalleexpedicionusers/{id}")
    @Timed
    public ResponseEntity<DetalleexpedicionuserDTO> getDetalleexpedicionuser(@PathVariable Long id) {
        log.debug("REST request to get Detalleexpedicionuser : {}", id);
        DetalleexpedicionuserDTO detalleexpedicionuserDTO = detalleexpedicionuserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(detalleexpedicionuserDTO));
    }

    /**
     * DELETE  /detalleexpedicionusers/:id : delete the "id" detalleexpedicionuser.
     *
     * @param id the id of the detalleexpedicionuserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/detalleexpedicionusers/{id}")
    @Timed
    public ResponseEntity<Void> deleteDetalleexpedicionuser(@PathVariable Long id) {
        log.debug("REST request to delete Detalleexpedicionuser : {}", id);
        detalleexpedicionuserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
