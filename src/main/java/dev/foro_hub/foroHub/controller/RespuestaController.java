package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.infra.error.Answer;
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
    public ResponseEntity postRegistrar(@RequestBody @Valid RegistrarRespuesta registrarRespuesta, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.findById(registrarRespuesta.idUsuario()).orElse(null);
        if(usuario == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false, 404,HttpStatus.NOT_FOUND,"El usuario ingresado no existe"));
        Topico topico = topicoRepository.findById(registrarRespuesta.idTopico()).orElse(null);
        if(topico == null || !topico.getStatus()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404,HttpStatus.NOT_FOUND,"El topico ingresado no existe"));
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false, 404, HttpStatus.NOT_FOUND,"La respuesta que deseas editar no existe"));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletEliminar(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.findById(id).orElse(null);
        if(respuesta == null || !respuesta.getStatus()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404,HttpStatus.NOT_FOUND,"La respuesta que deseas eliminar no existe"));
        respuesta = respuestaRepository.getReferenceById(id);
        respuesta.eliminar();
        return ResponseEntity.ok().body(new Answer(true,200,HttpStatus.OK,"Respuesta eliminado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCurso(@PathVariable Long id){
        Respuesta respuesta;
        if(respuestaRepository.existsById(id)){
            respuesta = respuestaRepository.findById(id).orElse(null);
            if(respuesta.getStatus()) {
                respuesta = respuestaRepository.getReferenceById(id);
                return ResponseEntity.ok(new RespuestaRespuesta(respuesta));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Answer(false,404,HttpStatus.NOT_FOUND,"La respuesta que deseas ver no exite"));
    }
}