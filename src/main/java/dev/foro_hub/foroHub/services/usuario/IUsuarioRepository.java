package dev.foro_hub.foroHub.services.usuario;

import dev.foro_hub.foroHub.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {

    Page<Usuario> findByStatusTrue(Pageable paginacion);
    Boolean existsByNombre(String nombre);
    Usuario findByNombre(String nombre);
}
