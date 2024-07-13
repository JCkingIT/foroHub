package dev.foro_hub.foroHub.services.perfil;

import dev.foro_hub.foroHub.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerfilRepository extends JpaRepository<Perfil,Long> {

    Page<Perfil> findByStatusTrue(Pageable paginacion);
    Boolean existsByNombre(String nombre);
    Perfil findByNombre(String nombre);
}
