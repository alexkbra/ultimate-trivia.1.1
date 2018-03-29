package co.com.elpoli.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Detalleexpedicionuser entity.
 */
public class DetalleexpedicionuserDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant fechaRespueta;

    @NotNull
    private Boolean respuesta;

    private Long preguntaId;

    private String preguntaCortaDescripcion;

    private Long expedicionuserId;

    private String expedicionuserNickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaRespueta() {
        return fechaRespueta;
    }

    public void setFechaRespueta(Instant fechaRespueta) {
        this.fechaRespueta = fechaRespueta;
    }

    public Boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Boolean respuesta) {
        this.respuesta = respuesta;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getPreguntaCortaDescripcion() {
        return preguntaCortaDescripcion;
    }

    public void setPreguntaCortaDescripcion(String preguntaCortaDescripcion) {
        this.preguntaCortaDescripcion = preguntaCortaDescripcion;
    }

    public Long getExpedicionuserId() {
        return expedicionuserId;
    }

    public void setExpedicionuserId(Long expedicionuserId) {
        this.expedicionuserId = expedicionuserId;
    }

    public String getExpedicionuserNickname() {
        return expedicionuserNickname;
    }

    public void setExpedicionuserNickname(String expedicionuserNickname) {
        this.expedicionuserNickname = expedicionuserNickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DetalleexpedicionuserDTO detalleexpedicionuserDTO = (DetalleexpedicionuserDTO) o;
        if(detalleexpedicionuserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), detalleexpedicionuserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DetalleexpedicionuserDTO{" +
            "id=" + getId() +
            ", fechaRespueta='" + getFechaRespueta() + "'" +
            ", respuesta='" + isRespuesta() + "'" +
            "}";
    }
}
