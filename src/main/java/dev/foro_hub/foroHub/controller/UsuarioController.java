package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.model.Perfil;
import dev.foro_hub.foroHub.model.Usuario;
import dev.foro_hub.foroHub.services.perfil.DatosPerfil;
import dev.foro_hub.foroHub.services.perfil.IPerfilRepository;
import dev.foro_hub.foroHub.services.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IPerfilRepository perfilRepository;

    @GetMapping
    public ResponseEntity<Page<ListadoUsuario>> getLista(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findByStatusTrue(paginacion).map(ListadoUsuario::new));
    }

    @PostMapping
    public ResponseEntity<RespuestaUsuario> postRegistrar(@RequestBody @Valid RegistrarUsuario registrarUsuario, UriComponentsBuilder uriComponentsBuilder){

        Perfil perfil = (registrarUsuario.idPerfil() != null)? perfilRepository.findById(registrarUsuario.idPerfil()).orElse(null) : perfilRepository.findByNombre("Estudiante");
        Usuario usuario = usuarioRepository.save(new Usuario(registrarUsuario,perfil));
        RespuestaUsuario respuestaUsuario = new RespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), new DatosPerfil(usuario.getPerfil().getNombre()));

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putActualizar(@RequestBody @Valid ActualizarUsuario actualizarUsuario){
        Usuario usuario;
        if(usuarioRepository.existsById(actualizarUsuario.id())){
            usuario = usuarioRepository.getById(actualizarUsuario.id());
            if(usuario.getStatus()) {
                usuario = usuarioRepository.getReferenceById(actualizarUsuario.id());
                usuario.actualizar(actualizarUsuario);
                return ResponseEntity.ok(new RespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), new DatosPerfil(usuario.getPerfil().getNombre())));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el curso solicitado o fue borrado");

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaUsuario> deletEliminar(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        Usuario usuario;
        if(usuarioRepository.existsById(id)){
            usuario = usuarioRepository.findById(id).orElse(null);
            if(usuario.getStatus()) {
                usuario = usuarioRepository.getReferenceById(id);
                System.out.println(usuario);
                return ResponseEntity.ok(new RespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), new DatosPerfil(usuario.getPerfil().getNombre())));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el usuario solicitado o fue borrado");
    }
}