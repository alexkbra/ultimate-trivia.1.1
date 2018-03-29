package co.com.elpoli.service.mapper;

import co.com.elpoli.domain.*;
import co.com.elpoli.service.dto.PreguntaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pregunta and its DTO PreguntaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PreguntaMapper extends EntityMapper <PreguntaDTO, Pregunta> {
    
    @Mapping(target = "respuestas", ignore = true)
    @Mapping(target = "detalleexpedicionusers", ignore = true)
    @Mapping(target = "nivels", ignore = true)
    Pregunta toEntity(PreguntaDTO preguntaDTO); 
    default Pregunta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pregunta pregunta = new Pregunta();
        pregunta.setId(id);
        return pregunta;
    }
}
