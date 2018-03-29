package co.com.elpoli.service.impl;

import co.com.elpoli.service.RespuestaService;
import co.com.elpoli.domain.Respuesta;
import co.com.elpoli.repository.RespuestaRepository;
import co.com.elpoli.service.dto.RespuestaDTO;
import co.com.elpoli.service.mapper.RespuestaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Respuesta.
 */
@Service
@Transactional
public class RespuestaServiceImpl implements RespuestaService{

    private final Logger log = LoggerFactory.getLogger(RespuestaServiceImpl.class);

    private final RespuestaRepository respuestaRepository;

    private final RespuestaMapper respuestaMapper;

    public RespuestaServiceImpl(RespuestaRepository respuestaRepository, RespuestaMapper respuestaMapper) {
        this.respuestaRepository = respuestaRepository;
        this.respuestaMapper = respuestaMapper;
    }

    /**
     * Save a respuesta.
     *
     * @param respuestaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RespuestaDTO save(RespuestaDTO respuestaDTO) {
        log.debug("Request to save Respuesta : {}", respuestaDTO);
        Respuesta respuesta = respuestaMapper.toEntity(respuestaDTO);
        respuesta = respuestaRepository.save(respuesta);
        return respuestaMapper.toDto(respuesta);
    }

    /**
     *  Get all the respuestas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespuestaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Respuestas");
        return respuestaRepository.findAll(pageable)
            .map(respuestaMapper::toDto);
    }

    /**
     *  Get one respuesta by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RespuestaDTO findOne(Long id) {
        log.debug("Request to get Respuesta : {}", id);
        Respuesta respuesta = respuestaRepository.findOne(id);
        return respuestaMapper.toDto(respuesta);
    }

    /**
     *  Delete the  respuesta by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Respuesta : {}", id);
        respuestaRepository.delete(id);
    }
}
