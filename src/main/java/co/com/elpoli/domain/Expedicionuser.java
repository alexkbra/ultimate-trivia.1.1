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
 * A Expedicionuser.
 */
@Entity
@Table(name = "expedicionuser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Expedicionuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "expedicionuser")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Detalleexpedicionuser> detalleexpedicionusers = new HashSet<>();

    @ManyToOne
    private User userid;

    @ManyToOne(optional = false)
    @NotNull
    private Expedicion expedicion;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public Expedicionuser nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Expedicionuser fechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Set<Detalleexpedicionuser> getDetalleexpedicionusers() {
        return detalleexpedicionusers;
    }

    public Expedicionuser detalleexpedicionusers(Set<Detalleexpedicionuser> detalleexpedicionusers) {
        this.detalleexpedicionusers = detalleexpedicionusers;
        return this;
    }

    public Expedicionuser addDetalleexpedicionuser(Detalleexpedicionuser detalleexpedicionuser) {
        this.detalleexpedicionusers.add(detalleexpedicionuser);
        detalleexpedicionuser.setExpedicionuser(this);
        return this;
    }

    public Expedicionuser removeDetalleexpedicionuser(Detalleexpedicionuser detalleexpedicionuser) {
        this.detalleexpedicionusers.remove(detalleexpedicionuser);
        detalleexpedicionuser.setExpedicionuser(null);
        return this;
    }

    public void setDetalleexpedicionusers(Set<Detalleexpedicionuser> detalleexpedicionusers) {
        this.detalleexpedicionusers = detalleexpedicionusers;
    }

    public User getUserid() {
        return userid;
    }

    public Expedicionuser userid(User user) {
        this.userid = user;
        return this;
    }

    public void setUserid(User user) {
        this.userid = user;
    }

    public Expedicion getExpedicion() {
        return expedicion;
    }

    public Expedicionuser expedicion(Expedicion expedicion) {
        this.expedicion = expedicion;
        return this;
    }

    public void setExpedicion(Expedicion expedicion) {
        this.expedicion = expedicion;
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
        Expedicionuser expedicionuser = (Expedicionuser) o;
        if (expedicionuser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), expedicionuser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Expedicionuser{" +
            "id=" + getId() +
            ", nickname='" + getNickname() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            "}";
    }
}
