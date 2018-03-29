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
 * A Expedicion.
 */
@Entity
@Table(name = "expedicion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Expedicion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10, max = 100)
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "expedicion")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Expedicionuser> expedicionusers = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "expedicion_cuestionario",
               joinColumns = @JoinColumn(name="expedicions_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="cuestionarios_id", referencedColumnName="id"))
    private Set<Cuestionario> cuestionarios = new HashSet<>();

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

    public Expedicion nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Expedicionuser> getExpedicionusers() {
        return expedicionusers;
    }

    public Expedicion expedicionusers(Set<Expedicionuser> expedicionusers) {
        this.expedicionusers = expedicionusers;
        return this;
    }

    public Expedicion addExpedicionuser(Expedicionuser expedicionuser) {
        this.expedicionusers.add(expedicionuser);
        expedicionuser.setExpedicion(this);
        return this;
    }

    public Expedicion removeExpedicionuser(Expedicionuser expedicionuser) {
        this.expedicionusers.remove(expedicionuser);
        expedicionuser.setExpedicion(null);
        return this;
    }

    public void setExpedicionusers(Set<Expedicionuser> expedicionusers) {
        this.expedicionusers = expedicionusers;
    }

    public Set<Cuestionario> getCuestionarios() {
        return cuestionarios;
    }

    public Expedicion cuestionarios(Set<Cuestionario> cuestionarios) {
        this.cuestionarios = cuestionarios;
        return this;
    }

    public Expedicion addCuestionario(Cuestionario cuestionario) {
        this.cuestionarios.add(cuestionario);
        cuestionario.getExpedicions().add(this);
        return this;
    }

    public Expedicion removeCuestionario(Cuestionario cuestionario) {
        this.cuestionarios.remove(cuestionario);
        cuestionario.getExpedicions().remove(this);
        return this;
    }

    public void setCuestionarios(Set<Cuestionario> cuestionarios) {
        this.cuestionarios = cuestionarios;
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
        Expedicion expedicion = (Expedicion) o;
        if (expedicion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), expedicion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Expedicion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
