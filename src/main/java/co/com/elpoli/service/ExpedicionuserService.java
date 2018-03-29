package co.com.elpoli.service;

import co.com.elpoli.service.dto.ExpedicionuserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Expedicionuser.
 */
public interface ExpedicionuserService {

    /**
     * Save a expedicionuser.
     *
     * @param expedicionuserDTO the entity to save
     * @return the persisted entity
     */
    ExpedicionuserDTO save(ExpedicionuserDTO expedicionuserDTO);

    /**
     *  Get all the expedicionusers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ExpedicionuserDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" expedicionuser.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ExpedicionuserDTO findOne(Long id);

    /**
     *  Delete the "id" expedicionuser.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
