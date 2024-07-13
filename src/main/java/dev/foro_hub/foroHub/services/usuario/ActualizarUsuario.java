package dev.foro_hub.foroHub.services.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuario(@NotNull Long id, String nombre, String clave) {
}
