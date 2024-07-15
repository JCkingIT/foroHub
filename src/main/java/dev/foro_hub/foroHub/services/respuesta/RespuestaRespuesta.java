package dev.foro_hub.foroHub.services.respuesta;

import dev.foro_hub.foroHub.model.Respuesta;
import dev.foro_hub.foroHub.services.topico.DatosTopico;
import dev.foro_hub.foroHub.services.usuario.DatosUsuario;

public record RespuestaRespuesta(Long id, String mensaje,Boolean solucion, DatosUsuario autor, DatosTopico topico) {
    public RespuestaRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(),respuesta.getSolucion(), new DatosUsuario(respuesta.getUsuario()), new DatosTopico(respuesta.getTopico()));
    }
}
