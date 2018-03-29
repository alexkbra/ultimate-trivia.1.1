package co.com.elpoli.repository;

import co.com.elpoli.domain.Expedicionuser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Expedicionuser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExpedicionuserRepository extends JpaRepository<Expedicionuser, Long> {

    @Query("select expedicionuser from Expedicionuser expedicionuser where expedicionuser.userid.login = ?#{principal.username}")
    List<Expedicionuser> findByUseridIsCurrentUser();

}
