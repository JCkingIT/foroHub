package dev.foro_hub.foroHub.services.perfil;

import jakarta.validation.constraints.NotNull;

public record ActualizarPerfil(@NotNull Long id, String nombre) {
}
