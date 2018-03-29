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

import co.com.elpoli.domain.enumeration.TipoArchivo;

/**
 * A Galerias.
 */
@Entity
@Table(name = "galerias")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Galerias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "uri", length = 500, nullable = false)
    private String uri;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_archivo", nullable = false)
    private TipoArchivo tipoArchivo;

    @ManyToMany(mappedBy = "galerias")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Publicidad> publicidads = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public Galerias uri(String uri) {
        this.uri = uri;
        return this;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public TipoArchivo getTipoArchivo() {
        return tipoArchivo;
    }

    public Galerias tipoArchivo(TipoArchivo tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
        return this;
    }

    public void setTipoArchivo(TipoArchivo tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Set<Publicidad> getPublicidads() {
        return publicidads;
    }

    public Galerias publicidads(Set<Publicidad> publicidads) {
        this.publicidads = publicidads;
        return this;
    }

    public Galerias addPublicidad(Publicidad publicidad) {
        this.publicidads.add(publicidad);
        publicidad.getGalerias().add(this);
        return this;
    }

    public Galerias removePublicidad(Publicidad publicidad) {
        this.publicidads.remove(publicidad);
        publicidad.getGalerias().remove(this);
        return this;
    }

    public void setPublicidads(Set<Publicidad> publicidads) {
        this.publicidads = publicidads;
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
        Galerias galerias = (Galerias) o;
        if (galerias.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), galerias.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Galerias{" +
            "id=" + getId() +
            ", uri='" + getUri() + "'" +
            ", tipoArchivo='" + getTipoArchivo() + "'" +
            "}";
    }
}
