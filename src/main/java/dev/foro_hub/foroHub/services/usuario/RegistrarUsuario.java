package dev.foro_hub.foroHub.services.usuario;

import jakarta.validation.constraints.NotBlank;

public record RegistrarUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String clave,
        Long idPerfil

) {
}
