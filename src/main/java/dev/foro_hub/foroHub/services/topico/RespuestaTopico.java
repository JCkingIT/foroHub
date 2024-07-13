package dev.foro_hub.foroHub.services.topico;

import dev.foro_hub.foroHub.model.Topico;
import dev.foro_hub.foroHub.services.curso.RespuestaCurso;
import dev.foro_hub.foroHub.services.usuario.RespuestaUsuario;

import java.time.LocalDateTime;

public record RespuestaTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, RespuestaUsuario autor, RespuestaCurso curso) {
    public RespuestaTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(),new RespuestaUsuario(topico.getUsuario()),new RespuestaCurso(topico.getCurso()));
    }
}
