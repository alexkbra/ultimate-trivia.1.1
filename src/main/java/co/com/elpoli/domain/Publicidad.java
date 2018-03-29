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
 * A Publicidad.
 */
@Entity
@Table(name = "publicidad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Publicidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @NotNull
    @Size(min = 10, max = 100)
    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @NotNull
    @Size(min = 10, max = 100)
    @Column(name = "premiodescripcion", length = 100, nullable = false)
    private String premiodescripcion;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "publicidad_galeria",
               joinColumns = @JoinColumn(name="publicidads_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="galerias_id", referencedColumnName="id"))
    private Set<Galerias> galerias = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    private Empresas empresas;

    @ManyToMany(mappedBy = "publicidads")
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

    public String getTitulo() {
        return titulo;
    }

    public Publicidad titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Publicidad descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPremiodescripcion() {
        return premiodescripcion;
    }

    public Publicidad premiodescripcion(String premiodescripcion) {
        this.premiodescripcion = premiodescripcion;
        return this;
    }

    public void setPremiodescripcion(String premiodescripcion) {
        this.premiodescripcion = premiodescripcion;
    }

    public Set<Galerias> getGalerias() {
        return galerias;
    }

    public Publicidad galerias(Set<Galerias> galerias) {
        this.galerias = galerias;
        return this;
    }

    public Publicidad addGaleria(Galerias galerias) {
        this.galerias.add(galerias);
        galerias.getPublicidads().add(this);
        return this;
    }

    public Publicidad removeGaleria(Galerias galerias) {
        this.galerias.remove(galerias);
        galerias.getPublicidads().remove(this);
        return this;
    }

    public void setGalerias(Set<Galerias> galerias) {
        this.galerias = galerias;
    }

    public Empresas getEmpresas() {
        return empresas;
    }

    public Publicidad empresas(Empresas empresas) {
        this.empresas = empresas;
        return this;
    }

    public void setEmpresas(Empresas empresas) {
        this.empresas = empresas;
    }

    public Set<Nivel> getNivels() {
        return nivels;
    }

    public Publicidad nivels(Set<Nivel> nivels) {
        this.nivels = nivels;
        return this;
    }

    public Publicidad addNivel(Nivel nivel) {
        this.nivels.add(nivel);
        nivel.getPublicidads().add(this);
        return this;
    }

    public Publicidad removeNivel(Nivel nivel) {
        this.nivels.remove(nivel);
        nivel.getPublicidads().remove(this);
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
        Publicidad publicidad = (Publicidad) o;
        if (publicidad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), publicidad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Publicidad{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", premiodescripcion='" + getPremiodescripcion() + "'" +
            "}";
    }
}
