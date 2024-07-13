package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.model.Curso;
import dev.foro_hub.foroHub.model.Topico;
import dev.foro_hub.foroHub.model.Usuario;
import dev.foro_hub.foroHub.services.curso.ICursoRepository;
import dev.foro_hub.foroHub.services.respuesta.IRespuestaRepository;
import dev.foro_hub.foroHub.services.topico.*;
import dev.foro_hub.foroHub.services.usuario.IUsuarioRepository;
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
@RequestMapping(value = "/topico")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IRespuestaRepository respuestaRepository;

    @GetMapping
    public ResponseEntity<Page<ListadoTopico>> getLista(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(ListadoTopico::new));
    }

    @PostMapping
    public ResponseEntity<RespuestaTopico> postRegistrar(@RequestBody @Valid RegistrarTopico registrarTopico, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.findById(registrarTopico.idUsuario()).orElse(null);
        Curso curso = cursoRepository.findById(registrarTopico.idCurso()).orElse(null);
        Topico topico = topicoRepository.save(new Topico(registrarTopico, usuario, curso));
        RespuestaTopico respuestaTopico = new RespuestaTopico(topico);

        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putActualizar(@RequestBody @Valid ActualizarTopico actualizarTopico){
        Topico topico;
        if(topicoRepository.existsById(actualizarTopico.id())){
            topico = topicoRepository.getById(actualizarTopico.id());
            if(topico.getStatus()) {
                topico = topicoRepository.getReferenceById(actualizarTopico.id());
                topico.actualizar(actualizarTopico);
                return ResponseEntity.ok(new RespuestaTopico(topico));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el topico solicitado o fue borrado");

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaTopico> deletEliminar(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCurso(@PathVariable Long id){

        Topico topico;

        if(topicoRepository.existsById(id)){
            topico = topicoRepository.findById(id).orElse(null);
            if(topico.getStatus()) {
                topico = topicoRepository.getReferenceById(id);
                return ResponseEntity.ok(new TopicoRespuestas(topico));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el topico solicitado o fue borrado");
    }
}