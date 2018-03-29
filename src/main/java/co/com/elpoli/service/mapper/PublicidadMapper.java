package co.com.elpoli.service.mapper;

import co.com.elpoli.domain.*;
import co.com.elpoli.service.dto.PublicidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Publicidad and its DTO PublicidadDTO.
 */
@Mapper(componentModel = "spring", uses = {GaleriasMapper.class, EmpresasMapper.class, })
public interface PublicidadMapper extends EntityMapper <PublicidadDTO, Publicidad> {

    @Mapping(source = "empresas.id", target = "empresasId")
    @Mapping(source = "empresas.razonSocial", target = "empresasRazonSocial")
    PublicidadDTO toDto(Publicidad publicidad); 

    @Mapping(source = "empresasId", target = "empresas")
    @Mapping(target = "nivels", ignore = true)
    Publicidad toEntity(PublicidadDTO publicidadDTO); 
    default Publicidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Publicidad publicidad = new Publicidad();
        publicidad.setId(id);
        return publicidad;
    }
}
