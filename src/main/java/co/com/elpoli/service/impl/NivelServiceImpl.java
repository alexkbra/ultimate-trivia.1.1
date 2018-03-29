package co.com.elpoli.service.impl;

import co.com.elpoli.service.NivelService;
import co.com.elpoli.domain.Nivel;
import co.com.elpoli.repository.NivelRepository;
import co.com.elpoli.service.dto.NivelDTO;
import co.com.elpoli.service.mapper.NivelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Nivel.
 */
@Service
@Transactional
public class NivelServiceImpl implements NivelService{

    private final Logger log = LoggerFactory.getLogger(NivelServiceImpl.class);

    private final NivelRepository nivelRepository;

    private final NivelMapper nivelMapper;

    public NivelServiceImpl(NivelRepository nivelRepository, NivelMapper nivelMapper) {
        this.nivelRepository = nivelRepository;
        this.nivelMapper = nivelMapper;
    }

    /**
     * Save a nivel.
     *
     * @param nivelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NivelDTO save(NivelDTO nivelDTO) {
        log.debug("Request to save Nivel : {}", nivelDTO);
        Nivel nivel = nivelMapper.toEntity(nivelDTO);
        nivel = nivelRepository.save(nivel);
        return nivelMapper.toDto(nivel);
    }

    /**
     *  Get all the nivels.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NivelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Nivels");
        return nivelRepository.findAll(pageable)
            .map(nivelMapper::toDto);
    }

    /**
     *  Get one nivel by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public NivelDTO findOne(Long id) {
        log.debug("Request to get Nivel : {}", id);
        Nivel nivel = nivelRepository.findOneWithEagerRelationships(id);
        return nivelMapper.toDto(nivel);
    }

    /**
     *  Delete the  nivel by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nivel : {}", id);
        nivelRepository.delete(id);
    }
}
