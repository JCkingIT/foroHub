package dev.foro_hub.foroHub.services.topico;

import dev.foro_hub.foroHub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico,Long> {

    Page<Topico> findByStatusTrue(Pageable paginacion);

    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);
}
