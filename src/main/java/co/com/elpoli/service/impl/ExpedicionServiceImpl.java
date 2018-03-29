package co.com.elpoli.service.impl;

import co.com.elpoli.service.ExpedicionService;
import co.com.elpoli.domain.Expedicion;
import co.com.elpoli.repository.ExpedicionRepository;
import co.com.elpoli.service.dto.ExpedicionDTO;
import co.com.elpoli.service.mapper.ExpedicionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Expedicion.
 */
@Service
@Transactional
public class ExpedicionServiceImpl implements ExpedicionService{

    private final Logger log = LoggerFactory.getLogger(ExpedicionServiceImpl.class);

    private final ExpedicionRepository expedicionRepository;

    private final ExpedicionMapper expedicionMapper;

    public ExpedicionServiceImpl(ExpedicionRepository expedicionRepository, ExpedicionMapper expedicionMapper) {
        this.expedicionRepository = expedicionRepository;
        this.expedicionMapper = expedicionMapper;
    }

    /**
     * Save a expedicion.
     *
     * @param expedicionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ExpedicionDTO save(ExpedicionDTO expedicionDTO) {
        log.debug("Request to save Expedicion : {}", expedicionDTO);
        Expedicion expedicion = expedicionMapper.toEntity(expedicionDTO);
        expedicion = expedicionRepository.save(expedicion);
        return expedicionMapper.toDto(expedicion);
    }

    /**
     *  Get all the expedicions.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ExpedicionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Expedicions");
        return expedicionRepository.findAll(pageable)
            .map(expedicionMapper::toDto);
    }

    /**
     *  Get one expedicion by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ExpedicionDTO findOne(Long id) {
        log.debug("Request to get Expedicion : {}", id);
        Expedicion expedicion = expedicionRepository.findOneWithEagerRelationships(id);
        return expedicionMapper.toDto(expedicion);
    }

    /**
     *  Delete the  expedicion by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Expedicion : {}", id);
        expedicionRepository.delete(id);
    }
}
