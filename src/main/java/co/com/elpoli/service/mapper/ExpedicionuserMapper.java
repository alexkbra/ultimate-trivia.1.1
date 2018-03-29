package co.com.elpoli.service.mapper;

import co.com.elpoli.domain.*;
import co.com.elpoli.service.dto.ExpedicionuserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Expedicionuser and its DTO ExpedicionuserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ExpedicionMapper.class, })
public interface ExpedicionuserMapper extends EntityMapper <ExpedicionuserDTO, Expedicionuser> {

    @Mapping(source = "userid.id", target = "useridId")

    @Mapping(source = "expedicion.id", target = "expedicionId")
    @Mapping(source = "expedicion.nombre", target = "expedicionNombre")
    ExpedicionuserDTO toDto(Expedicionuser expedicionuser); 
    @Mapping(target = "detalleexpedicionusers", ignore = true)

    @Mapping(source = "useridId", target = "userid")

    @Mapping(source = "expedicionId", target = "expedicion")
    Expedicionuser toEntity(ExpedicionuserDTO expedicionuserDTO); 
    default Expedicionuser fromId(Long id) {
        if (id == null) {
            return null;
        }
        Expedicionuser expedicionuser = new Expedicionuser();
        expedicionuser.setId(id);
        return expedicionuser;
    }
}
