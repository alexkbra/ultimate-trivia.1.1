package co.com.elpoli.repository;

import co.com.elpoli.domain.Nivel;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Nivel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {
    @Query("select distinct nivel from Nivel nivel left join fetch nivel.publicidads left join fetch nivel.preguntas")
    List<Nivel> findAllWithEagerRelationships();

    @Query("select nivel from Nivel nivel left join fetch nivel.publicidads left join fetch nivel.preguntas where nivel.id =:id")
    Nivel findOneWithEagerRelationships(@Param("id") Long id);

}
