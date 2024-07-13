package dev.foro_hub.foroHub.services.respuesta;

import jakarta.validation.constraints.NotNull;

public record ActualizarRespuesta(@NotNull Long id, String mensaje, Boolean solucion) {
}
