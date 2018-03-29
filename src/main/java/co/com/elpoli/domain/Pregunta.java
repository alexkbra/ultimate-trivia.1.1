package co.com.elpoli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import co.com.elpoli.domain.enumeration.TipoPregunta;

/**
 * A Pregunta.
 */
@Entity
@Table(name = "pregunta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10, max = 200)
    @Column(name = "corta_descripcion", length = 200, nullable = false)
    private String cortaDescripcion;

    @NotNull
    @Size(min = 10, max = 500)
    @Column(name = "descripcion", length = 500, nullable = false)
    private String descripcion;

    @NotNull
    @Size(min = 10, max = 500)
    @Column(name = "pista", length = 500, nullable = false)
    private String pista;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pregunta", nullable = false)
    private TipoPregunta tipoPregunta;

    @OneToMany(mappedBy = "pregunta")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Respuesta> respuestas = new HashSet<>();

    @OneToMany(mappedBy = "pregunta")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Detalleexpedicionuser> detalleexpedicionusers = new HashSet<>();

    @ManyToMany(mappedBy = "preguntas")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Nivel> nivels = new HashSet<>();

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

    public Pregunta cortaDescripcion(String cortaDescripcion) {
        this.cortaDescripcion = cortaDescripcion;
        return this;
    }

    public void setCortaDescripcion(String cortaDescripcion) {
        this.cortaDescripcion = cortaDescripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Pregunta descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPista() {
        return pista;
    }

    public Pregunta pista(String pista) {
        this.pista = pista;
        return this;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public Pregunta tipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
        return this;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Set<Respuesta> getRespuestas() {
        return respuestas;
    }

    public Pregunta respuestas(Set<Respuesta> respuestas) {
        this.respuestas = respuestas;
        return this;
    }

    public Pregunta addRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
        respuesta.setPregunta(this);
        return this;
    }

    public Pregunta removeRespuesta(Respuesta respuesta) {
        this.respuestas.remove(respuesta);
        respuesta.setPregunta(null);
        return this;
    }

    public void setRespuestas(Set<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Set<Detalleexpedicionuser> getDetalleexpedicionusers() {
        return detalleexpedicionusers;
    }

    public Pregunta detalleexpedicionusers(Set<Detalleexpedicionuser> detalleexpedicionusers) {
        this.detalleexpedicionusers = detalleexpedicionusers;
        return this;
    }

    public Pregunta addDetalleexpedicionuser(Detalleexpedicionuser detalleexpedicionuser) {
        this.detalleexpedicionusers.add(detalleexpedicionuser);
        detalleexpedicionuser.setPregunta(this);
        return this;
    }

    public Pregunta removeDetalleexpedicionuser(Detalleexpedicionuser detalleexpedicionuser) {
        this.detalleexpedicionusers.remove(detalleexpedicionuser);
        detalleexpedicionuser.setPregunta(null);
        return this;
    }

    public void setDetalleexpedicionusers(Set<Detalleexpedicionuser> detalleexpedicionusers) {
        this.detalleexpedicionusers = detalleexpedicionusers;
    }

    public Set<Nivel> getNivels() {
        return nivels;
    }

    public Pregunta nivels(Set<Nivel> nivels) {
        this.nivels = nivels;
        return this;
    }

    public Pregunta addNivel(Nivel nivel) {
        this.nivels.add(nivel);
        nivel.getPreguntas().add(this);
        return this;
    }

    public Pregunta removeNivel(Nivel nivel) {
        this.nivels.remove(nivel);
        nivel.getPreguntas().remove(this);
        return this;
    }

    public void setNivels(Set<Nivel> nivels) {
        this.nivels = nivels;
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
        Pregunta pregunta = (Pregunta) o;
        if (pregunta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pregunta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pregunta{" +
            "id=" + getId() +
            ", cortaDescripcion='" + getCortaDescripcion() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", pista='" + getPista() + "'" +
            ", tipoPregunta='" + getTipoPregunta() + "'" +
            "}";
    }
}
