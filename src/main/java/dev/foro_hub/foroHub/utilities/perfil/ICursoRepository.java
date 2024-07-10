package dev.foro_hub.foroHub.utilities.perfil;

import dev.foro_hub.foroHub.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {

    Page<Curso> findByStatusTrue(Pageable paginacion);
    Boolean existsByNombre(String nombre);
    Curso findByNombre(String nombre);
}
