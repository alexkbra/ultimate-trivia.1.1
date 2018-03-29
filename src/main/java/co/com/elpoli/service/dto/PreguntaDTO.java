package co.com.elpoli.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import co.com.elpoli.domain.enumeration.TipoPregunta;

/**
 * A DTO for the Pregunta entity.
 */
public class PreguntaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 10, max = 200)
    private String cortaDescripcion;

    @NotNull
    @Size(min = 10, max = 500)
    private String descripcion;

    @NotNull
    @Size(min = 10, max = 500)
    private String pista;

    @NotNull
    private TipoPregunta tipoPregunta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCortaDescripcion() {
        return cortaDescripcion;
    }

    public void setCortaDescripcion(String cortaDescripcion) {
        this.cortaDescripcion = cortaDescripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PreguntaDTO preguntaDTO = (PreguntaDTO) o;
        if(preguntaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), preguntaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PreguntaDTO{" +
            "id=" + getId() +
            ", cortaDescripcion='" + getCortaDescripcion() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", pista='" + getPista() + "'" +
            ", tipoPregunta='" + getTipoPregunta() + "'" +
            "}";
    }
}
