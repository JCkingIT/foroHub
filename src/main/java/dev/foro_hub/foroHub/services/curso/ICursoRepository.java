package dev.foro_hub.foroHub.services.curso;

import dev.foro_hub.foroHub.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {

    Page<Curso> findByStatusTrue(Pageable paginacion);
}
