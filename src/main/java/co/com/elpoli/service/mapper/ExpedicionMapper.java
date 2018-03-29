package co.com.elpoli.service.mapper;

import co.com.elpoli.domain.*;
import co.com.elpoli.service.dto.ExpedicionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Expedicion and its DTO ExpedicionDTO.
 */
@Mapper(componentModel = "spring", uses = {CuestionarioMapper.class, })
public interface ExpedicionMapper extends EntityMapper <ExpedicionDTO, Expedicion> {
    
    @Mapping(target = "expedicionusers", ignore = true)
    Expedicion toEntity(ExpedicionDTO expedicionDTO); 
    default Expedicion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Expedicion expedicion = new Expedicion();
        expedicion.setId(id);
        return expedicion;
    }
}
