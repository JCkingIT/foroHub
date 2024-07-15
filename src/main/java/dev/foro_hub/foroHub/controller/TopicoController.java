package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.infra.error.Answer;
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
    public ResponseEntity<Page<ListadoTopico>> all(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(ListadoTopico::new));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody @Valid RegistrarTopico registrarTopico, UriComponentsBuilder uriComponentsBuilder){
        if(topicoRepository.existsByTitulo(registrarTopico.titulo()) || topicoRepository.existsByMensaje(registrarTopico.mensaje())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false, 404, HttpStatus.NOT_FOUND,"Ya existe el topico con el titulo y/o mensaje ingresado"));
        }

        Usuario usuario = usuarioRepository.findById(registrarTopico.idUsuario()).orElse(null);
        if(usuario == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404, HttpStatus.NOT_FOUND,"El usuario ingresado no existe"));
        Curso curso = cursoRepository.findById(registrarTopico.idCurso()).orElse(null);
        if(curso == null || !curso.getStatus()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404, HttpStatus.NOT_FOUND,"El curso ingresado no existe"));

        Topico topico = topicoRepository.save(new Topico(registrarTopico, usuario, curso));
        RespuestaTopico respuestaTopico = new RespuestaTopico(topico);

        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid ActualizarTopico actualizarTopico){
        if(topicoRepository.existsByTitulo(actualizarTopico.titulo()) || topicoRepository.existsByMensaje(actualizarTopico.mensaje())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false, 404, HttpStatus.NOT_FOUND,"Ya existe el topico con el titulo y/o mensaje ingresado"));
        }

        Topico topico;
        if(topicoRepository.existsById(actualizarTopico.id())){
            topico = topicoRepository.getById(actualizarTopico.id());
            if(topico.getStatus()) {
                topico = topicoRepository.getReferenceById(actualizarTopico.id());
                topico.actualizar(actualizarTopico);
                return ResponseEntity.ok(new RespuestaTopico(topico));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404,HttpStatus.NOT_FOUND,"El topico que deseas editar no existe"));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Topico topico = topicoRepository.findById(id).orElse(null);
        if(topico == null || !topico.getStatus()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404, HttpStatus.NOT_FOUND,"El topico ingresado no existe"));
        topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.status(HttpStatus.OK).body(new Answer(true,200, HttpStatus.OK,"Topico eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity search(@PathVariable Long id){

        Topico topico;

        if(topicoRepository.existsById(id)){
            topico = topicoRepository.findById(id).orElse(null);
            if(topico.getStatus()) {
                topico = topicoRepository.getReferenceById(id);
                return ResponseEntity.ok(new TopicoRespuestas(topico));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404, HttpStatus.NOT_FOUND,"El topico que deseas ver no existe"));
    }
}