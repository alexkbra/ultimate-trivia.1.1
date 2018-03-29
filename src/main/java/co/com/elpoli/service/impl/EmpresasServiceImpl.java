package co.com.elpoli.service.impl;

import co.com.elpoli.service.EmpresasService;
import co.com.elpoli.domain.Empresas;
import co.com.elpoli.repository.EmpresasRepository;
import co.com.elpoli.service.dto.EmpresasDTO;
import co.com.elpoli.service.mapper.EmpresasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Empresas.
 */
@Service
@Transactional
public class EmpresasServiceImpl implements EmpresasService{

    private final Logger log = LoggerFactory.getLogger(EmpresasServiceImpl.class);

    private final EmpresasRepository empresasRepository;

    private final EmpresasMapper empresasMapper;

    public EmpresasServiceImpl(EmpresasRepository empresasRepository, EmpresasMapper empresasMapper) {
        this.empresasRepository = empresasRepository;
        this.empresasMapper = empresasMapper;
    }

    /**
     * Save a empresas.
     *
     * @param empresasDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmpresasDTO save(EmpresasDTO empresasDTO) {
        log.debug("Request to save Empresas : {}", empresasDTO);
        Empresas empresas = empresasMapper.toEntity(empresasDTO);
        empresas = empresasRepository.save(empresas);
        return empresasMapper.toDto(empresas);
    }

    /**
     *  Get all the empresas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmpresasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Empresas");
        return empresasRepository.findAll(pageable)
            .map(empresasMapper::toDto);
    }

    /**
     *  Get one empresas by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EmpresasDTO findOne(Long id) {
        log.debug("Request to get Empresas : {}", id);
        Empresas empresas = empresasRepository.findOne(id);
        return empresasMapper.toDto(empresas);
    }

    /**
     *  Delete the  empresas by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Empresas : {}", id);
        empresasRepository.delete(id);
    }
}
