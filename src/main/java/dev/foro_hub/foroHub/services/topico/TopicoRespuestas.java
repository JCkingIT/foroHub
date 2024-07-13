package dev.foro_hub.foroHub.services.topico;

import dev.foro_hub.foroHub.model.Topico;
import dev.foro_hub.foroHub.services.curso.DatosCurso;
import dev.foro_hub.foroHub.services.respuesta.DatosRespuesta;
import dev.foro_hub.foroHub.services.usuario.DatosUsuario;
import dev.foro_hub.foroHub.utilities.Convertir;
import java.time.LocalDateTime;
import java.util.List;

public record TopicoRespuestas(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, DatosUsuario autor, DatosCurso curso, List<DatosRespuesta> respuestas) {
    public TopicoRespuestas(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),new DatosUsuario(topico.getUsuario()),new DatosCurso(topico.getCurso()), Convertir.listaRespuesta(topico.getRespuestas()));
    }
}
