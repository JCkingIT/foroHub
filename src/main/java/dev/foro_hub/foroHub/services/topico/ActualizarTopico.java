package dev.foro_hub.foroHub.services.topico;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopico(@NotNull Long id, String titulo, String mensaje) {
}
