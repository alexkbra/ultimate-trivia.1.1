package co.com.elpoli.service.impl;

import co.com.elpoli.service.ExpedicionuserService;
import co.com.elpoli.domain.Expedicionuser;
import co.com.elpoli.repository.ExpedicionuserRepository;
import co.com.elpoli.service.dto.ExpedicionuserDTO;
import co.com.elpoli.service.mapper.ExpedicionuserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Expedicionuser.
 */
@Service
@Transactional
public class ExpedicionuserServiceImpl implements ExpedicionuserService{

    private final Logger log = LoggerFactory.getLogger(ExpedicionuserServiceImpl.class);

    private final ExpedicionuserRepository expedicionuserRepository;

    private final ExpedicionuserMapper expedicionuserMapper;

    public ExpedicionuserServiceImpl(ExpedicionuserRepository expedicionuserRepository, ExpedicionuserMapper expedicionuserMapper) {
        this.expedicionuserRepository = expedicionuserRepository;
        this.expedicionuserMapper = expedicionuserMapper;
    }

    /**
     * Save a expedicionuser.
     *
     * @param expedicionuserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ExpedicionuserDTO save(ExpedicionuserDTO expedicionuserDTO) {
        log.debug("Request to save Expedicionuser : {}", expedicionuserDTO);
        Expedicionuser expedicionuser = expedicionuserMapper.toEntity(expedicionuserDTO);
        expedicionuser = expedicionuserRepository.save(expedicionuser);
        return expedicionuserMapper.toDto(expedicionuser);
    }

    /**
     *  Get all the expedicionusers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ExpedicionuserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Expedicionusers");
        return expedicionuserRepository.findAll(pageable)
            .map(expedicionuserMapper::toDto);
    }

    /**
     *  Get one expedicionuser by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ExpedicionuserDTO findOne(Long id) {
        log.debug("Request to get Expedicionuser : {}", id);
        Expedicionuser expedicionuser = expedicionuserRepository.findOne(id);
        return expedicionuserMapper.toDto(expedicionuser);
    }

    /**
     *  Delete the  expedicionuser by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Expedicionuser : {}", id);
        expedicionuserRepository.delete(id);
    }
}
