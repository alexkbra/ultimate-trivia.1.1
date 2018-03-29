package co.com.elpoli.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Expedicionuser entity.
 */
public class ExpedicionuserDTO implements Serializable {

    private Long id;

    @NotNull
    private String nickname;

    @NotNull
    private LocalDate fechaRegistro;

    private Long useridId;

    private Long expedicionId;

    private String expedicionNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getUseridId() {
        return useridId;
    }

    public void setUseridId(Long userId) {
        this.useridId = userId;
    }

    public Long getExpedicionId() {
        return expedicionId;
    }

    public void setExpedicionId(Long expedicionId) {
        this.expedicionId = expedicionId;
    }

    public String getExpedicionNombre() {
        return expedicionNombre;
    }

    public void setExpedicionNombre(String expedicionNombre) {
        this.expedicionNombre = expedicionNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExpedicionuserDTO expedicionuserDTO = (ExpedicionuserDTO) o;
        if(expedicionuserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), expedicionuserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExpedicionuserDTO{" +
            "id=" + getId() +
            ", nickname='" + getNickname() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            "}";
    }
}
