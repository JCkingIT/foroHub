package dev.foro_hub.foroHub.utilities.perfil;

import jakarta.validation.constraints.NotBlank;

public record RegistrarCurso(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}
