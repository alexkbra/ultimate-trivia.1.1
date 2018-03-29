package co.com.elpoli.repository;

import co.com.elpoli.domain.Publicidad;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Publicidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicidadRepository extends JpaRepository<Publicidad, Long> {
    @Query("select distinct publicidad from Publicidad publicidad left join fetch publicidad.galerias")
    List<Publicidad> findAllWithEagerRelationships();

    @Query("select publicidad from Publicidad publicidad left join fetch publicidad.galerias where publicidad.id =:id")
    Publicidad findOneWithEagerRelationships(@Param("id") Long id);

}
