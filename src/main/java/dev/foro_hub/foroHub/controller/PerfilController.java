package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.model.Perfil;
import dev.foro_hub.foroHub.services.perfil.*;
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
@RequestMapping(value = "/perfil")
public class PerfilController {

    @Autowired
    private IPerfilRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListadoPerfil>> getLista(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(repository.findByStatusTrue(paginacion).map(ListadoPerfil::new));
    }

    @PostMapping
    public ResponseEntity<RespuestaPerfil> postRegistrar(@RequestBody @Valid RegistrarPerfil registrarPerfil, UriComponentsBuilder uriComponentsBuilder){
        Perfil perfil = repository.save(new Perfil(registrarPerfil));
        RespuestaPerfil respuestaPerfil = new RespuestaPerfil(perfil.getId(), perfil.getNombre());

        URI url = uriComponentsBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaPerfil);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putActualizar(@RequestBody @Valid ActualizarPerfil actualizarPerfil){
        Perfil perfil;
        if(repository.existsById(actualizarPerfil.id())){
            perfil = repository.getById(actualizarPerfil.id());
            if(perfil.getStatus()) {
                perfil = repository.getReferenceById(actualizarPerfil.id());
                perfil.actualizar(actualizarPerfil);
                return ResponseEntity.ok(new RespuestaPerfil(perfil.getId(), perfil.getNombre()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el perfil solicitado o fue borrado");

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaPerfil> deletEliminar(@PathVariable Long id){
        Perfil perfil = repository.getReferenceById(id);
        perfil.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPerfil(@PathVariable Long id){
        Perfil perfil;
        if(repository.existsById(id)){
            perfil = repository.findById(id).orElse(null);
            if(perfil.getStatus()) {
                perfil = repository.getReferenceById(id);
                return ResponseEntity.ok(new RespuestaPerfil(perfil.getId(), perfil.getNombre()));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el perfil solicitado o fue borrado");
    }
}