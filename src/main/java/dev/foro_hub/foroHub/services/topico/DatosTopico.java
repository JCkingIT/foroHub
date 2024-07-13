package dev.foro_hub.foroHub.services.topico;

import dev.foro_hub.foroHub.model.Topico;
import dev.foro_hub.foroHub.services.curso.DatosCurso;
import dev.foro_hub.foroHub.services.usuario.DatosUsuario;

import java.time.LocalDateTime;

public record DatosTopico(String titulo, String mensaje, LocalDateTime fechaCreacion, DatosUsuario autor, DatosCurso curso) {
    public DatosTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(),new DatosUsuario(topico.getUsuario()),new DatosCurso(topico.getCurso()));
    }
}
