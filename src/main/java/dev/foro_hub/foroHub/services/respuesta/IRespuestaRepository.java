package dev.foro_hub.foroHub.services.respuesta;

import dev.foro_hub.foroHub.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRespuestaRepository extends JpaRepository<Respuesta,Long> {
}
