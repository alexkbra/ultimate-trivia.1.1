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

/**
 * A Cuestionario.
 */
@Entity
@Table(name = "cuestionario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cuestionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "cuestionario_nivel",
               joinColumns = @JoinColumn(name="cuestionarios_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="nivels_id", referencedColumnName="id"))
    private Set<Nivel> nivels = new HashSet<>();

    @ManyToMany(mappedBy = "cuestionarios")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Expedicion> expedicions = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Cuestionario nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Nivel> getNivels() {
        return nivels;
    }

    public Cuestionario nivels(Set<Nivel> nivels) {
        this.nivels = nivels;
        return this;
    }

    public Cuestionario addNivel(Nivel nivel) {
        this.nivels.add(nivel);
        nivel.getCuestionarios().add(this);
        return this;
    }

    public Cuestionario removeNivel(Nivel nivel) {
        this.nivels.remove(nivel);
        nivel.getCuestionarios().remove(this);
        return this;
    }

    public void setNivels(Set<Nivel> nivels) {
        this.nivels = nivels;
    }

    public Set<Expedicion> getExpedicions() {
        return expedicions;
    }

    public Cuestionario expedicions(Set<Expedicion> expedicions) {
        this.expedicions = expedicions;
        return this;
    }

    public Cuestionario addExpedicion(Expedicion expedicion) {
        this.expedicions.add(expedicion);
        expedicion.getCuestionarios().add(this);
        return this;
    }

    public Cuestionario removeExpedicion(Expedicion expedicion) {
        this.expedicions.remove(expedicion);
        expedicion.getCuestionarios().remove(this);
        return this;
    }

    public void setExpedicions(Set<Expedicion> expedicions) {
        this.expedicions = expedicions;
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
        Cuestionario cuestionario = (Cuestionario) o;
        if (cuestionario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cuestionario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cuestionario{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
