package co.com.elpoli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Empresas.
 */
@Entity
@Table(name = "empresas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 200)
    @Column(name = "razon_social", length = 200, nullable = false)
    private String razonSocial;

    @NotNull
    @Min(value = 9L)
    @Max(value = 15L)
    @Column(name = "nit", nullable = false)
    private Long nit;

    @NotNull
    @Min(value = 1)
    @Max(value = 2)
    @Column(name = "digito_verificacion", nullable = false)
    private Integer digitoVerificacion;

    @NotNull
    @Min(value = 7L)
    @Max(value = 15L)
    @Column(name = "telefono", nullable = false)
    private Long telefono;

    @Min(value = 7L)
    @Max(value = 15L)
    @Column(name = "telefono_contacto")
    private Long telefonoContacto;

    @Size(max = 100)
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 500)
    @Column(name = "direccion", length = 500)
    private String direccion;

    @Size(max = 500)
    @Column(name = "pagina_web", length = 500)
    private String paginaWeb;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "empresas")
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public Empresas razonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        return this;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Long getNit() {
        return nit;
    }

    public Empresas nit(Long nit) {
        this.nit = nit;
        return this;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public Integer getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public Empresas digitoVerificacion(Integer digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
        return this;
    }

    public void setDigitoVerificacion(Integer digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public Empresas telefono(Long telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Long getTelefonoContacto() {
        return telefonoContacto;
    }

    public Empresas telefonoContacto(Long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
        return this;
    }

    public void setTelefonoContacto(Long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmail() {
        return email;
    }

    public Empresas email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public Empresas direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public Empresas paginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
        return this;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Empresas fechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Set<Publicidad> getPublicidads() {
        return publicidads;
    }

    public Empresas publicidads(Set<Publicidad> publicidads) {
        this.publicidads = publicidads;
        return this;
    }

    public Empresas addPublicidad(Publicidad publicidad) {
        this.publicidads.add(publicidad);
        publicidad.setEmpresas(this);
        return this;
    }

    public Empresas removePublicidad(Publicidad publicidad) {
        this.publicidads.remove(publicidad);
        publicidad.setEmpresas(null);
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
        Empresas empresas = (Empresas) o;
        if (empresas.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empresas.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Empresas{" +
            "id=" + getId() +
            ", razonSocial='" + getRazonSocial() + "'" +
            ", nit='" + getNit() + "'" +
            ", digitoVerificacion='" + getDigitoVerificacion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", telefonoContacto='" + getTelefonoContacto() + "'" +
            ", email='" + getEmail() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", paginaWeb='" + getPaginaWeb() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            "}";
    }
}
