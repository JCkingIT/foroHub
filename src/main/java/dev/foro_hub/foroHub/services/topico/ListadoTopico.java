package dev.foro_hub.foroHub.services.topico;

import dev.foro_hub.foroHub.model.*;
import dev.foro_hub.foroHub.services.curso.DatosCurso;
import dev.foro_hub.foroHub.services.usuario.DatosUsuario;

import java.time.LocalDateTime;

public record ListadoTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean status, DatosUsuario autor, DatosCurso curso) {
    public ListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),new DatosUsuario(topico.getUsuario()), new DatosCurso(topico.getCurso()));
    }
}
