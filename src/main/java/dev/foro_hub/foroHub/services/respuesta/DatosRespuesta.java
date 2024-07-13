package dev.foro_hub.foroHub.services.respuesta;

import dev.foro_hub.foroHub.model.Respuesta;

public record DatosRespuesta(String mensaje, String autor) {
    public DatosRespuesta(Respuesta respuesta){
        this(respuesta.getMensaje(),respuesta.getUsuario().getNombre());
    }
}
