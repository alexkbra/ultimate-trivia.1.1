package co.com.elpoli.service.mapper;

import co.com.elpoli.domain.*;
import co.com.elpoli.service.dto.DetalleexpedicionuserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Detalleexpedicionuser and its DTO DetalleexpedicionuserDTO.
 */
@Mapper(componentModel = "spring", uses = {PreguntaMapper.class, ExpedicionuserMapper.class, })
public interface DetalleexpedicionuserMapper extends EntityMapper <DetalleexpedicionuserDTO, Detalleexpedicionuser> {

    @Mapping(source = "pregunta.id", target = "preguntaId")
    @Mapping(source = "pregunta.cortaDescripcion", target = "preguntaCortaDescripcion")

    @Mapping(source = "expedicionuser.id", target = "expedicionuserId")
    @Mapping(source = "expedicionuser.nickname", target = "expedicionuserNickname")
    DetalleexpedicionuserDTO toDto(Detalleexpedicionuser detalleexpedicionuser); 

    @Mapping(source = "preguntaId", target = "pregunta")

    @Mapping(source = "expedicionuserId", target = "expedicionuser")
    Detalleexpedicionuser toEntity(DetalleexpedicionuserDTO detalleexpedicionuserDTO); 
    default Detalleexpedicionuser fromId(Long id) {
        if (id == null) {
            return null;
        }
        Detalleexpedicionuser detalleexpedicionuser = new Detalleexpedicionuser();
        detalleexpedicionuser.setId(id);
        return detalleexpedicionuser;
    }
}
