package dev.foro_hub.foroHub.utilities;

import dev.foro_hub.foroHub.model.Respuesta;
import dev.foro_hub.foroHub.services.respuesta.DatosRespuesta;

import java.util.ArrayList;
import java.util.List;

public class Convertir {
    static public List<DatosRespuesta> listaRespuesta(List<Respuesta> respuestas){
        List<DatosRespuesta> respuestaList = new ArrayList<>();
        respuestas.forEach(r -> respuestaList.add(new DatosRespuesta(r)));

        return respuestaList;
    }
}
