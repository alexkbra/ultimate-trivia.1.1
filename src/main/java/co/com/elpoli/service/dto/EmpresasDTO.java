package co.com.elpoli.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Empresas entity.
 */
public class EmpresasDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 4, max = 200)
    private String razonSocial;

    @NotNull
    @Min(value = 9L)
    @Max(value = 15L)
    private Long nit;

    @NotNull
    @Min(value = 1)
    @Max(value = 2)
    private Integer digitoVerificacion;

    @NotNull
    @Min(value = 7L)
    @Max(value = 15L)
    private Long telefono;

    @Min(value = 7L)
    @Max(value = 15L)
    private Long telefonoContacto;

    @Size(max = 100)
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    private String email;

    @Size(max = 500)
    private String direccion;

    @Size(max = 500)
    private String paginaWeb;

    @NotNull
    private LocalDate fechaRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public Integer getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(Integer digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Long getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(Long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmpresasDTO empresasDTO = (EmpresasDTO) o;
        if(empresasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empresasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmpresasDTO{" +
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
