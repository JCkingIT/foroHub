package dev.foro_hub.foroHub.services.topico;

import dev.foro_hub.foroHub.model.Topico;
import dev.foro_hub.foroHub.services.curso.RespuestaCurso;
import dev.foro_hub.foroHub.services.usuario.DatosUsuario;

import java.time.LocalDateTime;

public record RespuestaTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, DatosUsuario autor, RespuestaCurso curso) {
    public RespuestaTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(),new DatosUsuario(topico.getUsuario()),new RespuestaCurso(topico.getCurso()));
    }
}
