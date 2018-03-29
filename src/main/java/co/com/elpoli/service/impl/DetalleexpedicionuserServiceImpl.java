package co.com.elpoli.service.impl;

import co.com.elpoli.service.DetalleexpedicionuserService;
import co.com.elpoli.domain.Detalleexpedicionuser;
import co.com.elpoli.repository.DetalleexpedicionuserRepository;
import co.com.elpoli.service.dto.DetalleexpedicionuserDTO;
import co.com.elpoli.service.mapper.DetalleexpedicionuserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Detalleexpedicionuser.
 */
@Service
@Transactional
public class DetalleexpedicionuserServiceImpl implements DetalleexpedicionuserService{

    private final Logger log = LoggerFactory.getLogger(DetalleexpedicionuserServiceImpl.class);

    private final DetalleexpedicionuserRepository detalleexpedicionuserRepository;

    private final DetalleexpedicionuserMapper detalleexpedicionuserMapper;

    public DetalleexpedicionuserServiceImpl(DetalleexpedicionuserRepository detalleexpedicionuserRepository, DetalleexpedicionuserMapper detalleexpedicionuserMapper) {
        this.detalleexpedicionuserRepository = detalleexpedicionuserRepository;
        this.detalleexpedicionuserMapper = detalleexpedicionuserMapper;
    }

    /**
     * Save a detalleexpedicionuser.
     *
     * @param detalleexpedicionuserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DetalleexpedicionuserDTO save(DetalleexpedicionuserDTO detalleexpedicionuserDTO) {
        log.debug("Request to save Detalleexpedicionuser : {}", detalleexpedicionuserDTO);
        Detalleexpedicionuser detalleexpedicionuser = detalleexpedicionuserMapper.toEntity(detalleexpedicionuserDTO);
        detalleexpedicionuser = detalleexpedicionuserRepository.save(detalleexpedicionuser);
        return detalleexpedicionuserMapper.toDto(detalleexpedicionuser);
    }

    /**
     *  Get all the detalleexpedicionusers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DetalleexpedicionuserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Detalleexpedicionusers");
        return detalleexpedicionuserRepository.findAll(pageable)
            .map(detalleexpedicionuserMapper::toDto);
    }

    /**
     *  Get one detalleexpedicionuser by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DetalleexpedicionuserDTO findOne(Long id) {
        log.debug("Request to get Detalleexpedicionuser : {}", id);
        Detalleexpedicionuser detalleexpedicionuser = detalleexpedicionuserRepository.findOne(id);
        return detalleexpedicionuserMapper.toDto(detalleexpedicionuser);
    }

    /**
     *  Delete the  detalleexpedicionuser by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Detalleexpedicionuser : {}", id);
        detalleexpedicionuserRepository.delete(id);
    }
}
