package co.com.elpoli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Detalleexpedicionuser.
 */
@Entity
@Table(name = "detalleexpedicionuser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Detalleexpedicionuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fecha_respueta", nullable = false)
    private Instant fechaRespueta;

    @NotNull
    @Column(name = "respuesta", nullable = false)
    private Boolean respuesta;

    @ManyToOne(optional = false)
    @NotNull
    private Pregunta pregunta;

    @ManyToOne(optional = false)
    @NotNull
    private Expedicionuser expedicionuser;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaRespueta() {
        return fechaRespueta;
    }

    public Detalleexpedicionuser fechaRespueta(Instant fechaRespueta) {
        this.fechaRespueta = fechaRespueta;
        return this;
    }

    public void setFechaRespueta(Instant fechaRespueta) {
        this.fechaRespueta = fechaRespueta;
    }

    public Boolean isRespuesta() {
        return respuesta;
    }

    public Detalleexpedicionuser respuesta(Boolean respuesta) {
        this.respuesta = respuesta;
        return this;
    }

    public void setRespuesta(Boolean respuesta) {
        this.respuesta = respuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public Detalleexpedicionuser pregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
        return this;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Expedicionuser getExpedicionuser() {
        return expedicionuser;
    }

    public Detalleexpedicionuser expedicionuser(Expedicionuser expedicionuser) {
        this.expedicionuser = expedicionuser;
        return this;
    }

    public void setExpedicionuser(Expedicionuser expedicionuser) {
        this.expedicionuser = expedicionuser;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Detalleexpedicionuser detalleexpedicionuser = (Detalleexpedicionuser) o;
        if (detalleexpedicionuser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), detalleexpedicionuser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Detalleexpedicionuser{" +
            "id=" + getId() +
            ", fechaRespueta='" + getFechaRespueta() + "'" +
            ", respuesta='" + isRespuesta() + "'" +
            "}";
    }
}
