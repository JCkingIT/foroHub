package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.model.Respuesta;
import dev.foro_hub.foroHub.model.Topico;
import dev.foro_hub.foroHub.model.Usuario;
import dev.foro_hub.foroHub.services.respuesta.*;
import dev.foro_hub.foroHub.services.topico.ITopicoRepository;
import dev.foro_hub.foroHub.services.usuario.IUsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/respuesta")
public class RespuestaController {

    @Autowired
    private IRespuestaRepository respuestaRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private ITopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<RespuestaRespuesta> postRegistrar(@RequestBody @Valid RegistrarRespuesta registrarRespuesta, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.findById(registrarRespuesta.idUsuario()).orElse(null);
        Topico topico = topicoRepository.findById(registrarRespuesta.idTopico()).orElse(null);
        Respuesta respuesta = respuestaRepository.save(new Respuesta(registrarRespuesta,usuario,topico));
        RespuestaRespuesta respuestaRespuesta = new RespuestaRespuesta(respuesta);

        URI url = uriComponentsBuilder.path("/perfil/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putActualizar(@RequestBody @Valid ActualizarRespuesta actualizarRespuesta){
        Respuesta respuesta;
        if(respuestaRepository.existsById(actualizarRespuesta.id())){
            respuesta = respuestaRepository.getById(actualizarRespuesta.id());
            if(respuesta.getStatus()) {
                respuesta = respuestaRepository.getReferenceById(actualizarRespuesta.id());
                respuesta.actualizar(actualizarRespuesta);
                return ResponseEntity.ok(new RespuestaRespuesta(respuesta));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el perfil solicitado o fue borrado");

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaRespuesta> deletEliminar(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCurso(@PathVariable Long id){
        Respuesta respuesta;
        if(respuestaRepository.existsById(id)){
            System.out.println("no entro");
            respuesta = respuestaRepository.findById(id).orElse(null);
            if(respuesta.getStatus()) {
                respuesta = respuestaRepository.getReferenceById(id);
                return ResponseEntity.ok(new RespuestaRespuesta(respuesta));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el perfil solicitado o fue borrado");
    }
}