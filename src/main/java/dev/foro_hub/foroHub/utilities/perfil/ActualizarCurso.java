package dev.foro_hub.foroHub.utilities.perfil;

import jakarta.validation.constraints.NotNull;

public record ActualizarCurso(@NotNull Long id, String nombre, String categoria) {
}
