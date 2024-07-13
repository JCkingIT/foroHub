package dev.foro_hub.foroHub.services.curso;

import jakarta.validation.constraints.NotBlank;

public record RegistrarCurso(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}
