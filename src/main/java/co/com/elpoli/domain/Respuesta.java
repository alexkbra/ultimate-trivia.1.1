package co.com.elpoli.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Respuesta.
 */
@Entity
@Table(name = "respuesta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10, max = 200)
    @Column(name = "corta_descripcion", length = 200, nullable = false)
    private String cortaDescripcion;

    @NotNull
    @Size(min = 10, max = 600)
    @Column(name = "descripcion", length = 600, nullable = false)
    private String descripcion;

    @ManyToOne(optional = false)
    @NotNull
    private Pregunta pregunta;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCortaDescripcion() {
        return cortaDescripcion;
    }

    public Respuesta cortaDescripcion(String cortaDescripcion) {
        this.cortaDescripcion = cortaDescripcion;
        return this;
    }

    public void setCortaDescripcion(String cortaDescripcion) {
        this.cortaDescripcion = cortaDescripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Respuesta descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public Respuesta pregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
        return this;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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
        Respuesta respuesta = (Respuesta) o;
        if (respuesta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respuesta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Respuesta{" +
            "id=" + getId() +
            ", cortaDescripcion='" + getCortaDescripcion() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
