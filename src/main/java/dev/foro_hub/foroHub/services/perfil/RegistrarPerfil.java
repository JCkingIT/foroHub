package dev.foro_hub.foroHub.services.perfil;

import jakarta.validation.constraints.NotBlank;

public record RegistrarPerfil(
        @NotBlank
        String nombre
) {
}
