package co.com.elpoli.repository;

import co.com.elpoli.domain.Detalleexpedicionuser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Detalleexpedicionuser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetalleexpedicionuserRepository extends JpaRepository<Detalleexpedicionuser, Long> {

}
