package co.com.elpoli.service;

import co.com.elpoli.service.dto.DetalleexpedicionuserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Detalleexpedicionuser.
 */
public interface DetalleexpedicionuserService {

    /**
     * Save a detalleexpedicionuser.
     *
     * @param detalleexpedicionuserDTO the entity to save
     * @return the persisted entity
     */
    DetalleexpedicionuserDTO save(DetalleexpedicionuserDTO detalleexpedicionuserDTO);

    /**
     *  Get all the detalleexpedicionusers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DetalleexpedicionuserDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" detalleexpedicionuser.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DetalleexpedicionuserDTO findOne(Long id);

    /**
     *  Delete the "id" detalleexpedicionuser.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
