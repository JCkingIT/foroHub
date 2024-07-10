package dev.foro_hub.foroHub.utilities.curso;

import jakarta.validation.constraints.NotNull;

public record ActualizarCurso(@NotNull Long id, String nombre, String categoria) {
}
