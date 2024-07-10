package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.model.Curso;
import dev.foro_hub.foroHub.utilities.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
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
        Curso curso = repository.getReferenceById(actualizarCurso.id());
        curso.actualizar(actualizarCurso);
        return ResponseEntity.ok(new RespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaCurso> deletEliminar(@PathVariable Long id){
        Curso curso = repository.getReferenceById(id);
        curso.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaCurso> getCurso(@PathVariable Long id){
        Curso curso = repository.getReferenceById(id);
        var respuestaCurso = new RespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria());
        return ResponseEntity.ok(respuestaCurso);
    }
}