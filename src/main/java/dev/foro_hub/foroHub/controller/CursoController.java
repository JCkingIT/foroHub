package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.infra.error.Answer;
import dev.foro_hub.foroHub.model.Curso;
import dev.foro_hub.foroHub.services.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private ICursoRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListadoCurso>> getLista(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(repository.findByStatusTrue(paginacion).map(ListadoCurso::new));
    }

    @PostMapping
    public ResponseEntity<RespuestaCurso> postRegistrar(@RequestBody @Valid RegistrarCurso registrarCurso, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = repository.save(new Curso(registrarCurso));
        RespuestaCurso respuestaCurso = new RespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria());

        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaCurso);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putActualizar(@RequestBody @Valid ActualizarCurso actualizarCurso){
        Curso curso;
        if(repository.existsById(actualizarCurso.id())){
            curso = repository.getById(actualizarCurso.id());
            if(curso.getStatus()) {
                curso = repository.getReferenceById(actualizarCurso.id());
                curso.actualizar(actualizarCurso);
                return ResponseEntity.ok(new RespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false, 404,HttpStatus.NOT_FOUND,"El curso que deseas editar no existe"));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletEliminar(@PathVariable Long id){
        Curso curso = repository.findById(id).orElse(null);
        if(curso == null && !curso.getStatus()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false, 404,HttpStatus.NOT_FOUND,"El curso que deseas eliminar no existe"));
        curso = repository.getReferenceById(id);
        curso.eliminar();
        return ResponseEntity.ok().body(new Answer(true, 200, HttpStatus.OK,"Curso eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCurso(@PathVariable Long id){
        Curso curso;
        if(repository.existsById(id)){
            curso = repository.findById(id).orElse(null);
            if(curso.getStatus()) {
                curso = repository.getReferenceById(id);
                return ResponseEntity.ok(new RespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false, 404,HttpStatus.NOT_FOUND,"El curso solicitado no existe"));
    }
}