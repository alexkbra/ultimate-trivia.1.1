package co.com.elpoli.service.mapper;

import co.com.elpoli.domain.*;
import co.com.elpoli.service.dto.GaleriasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Galerias and its DTO GaleriasDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GaleriasMapper extends EntityMapper <GaleriasDTO, Galerias> {
    
    @Mapping(target = "publicidads", ignore = true)
    Galerias toEntity(GaleriasDTO galeriasDTO); 
    default Galerias fromId(Long id) {
        if (id == null) {
            return null;
        }
        Galerias galerias = new Galerias();
        galerias.setId(id);
        return galerias;
    }
}
