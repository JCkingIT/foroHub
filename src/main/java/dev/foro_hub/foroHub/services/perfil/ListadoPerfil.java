package dev.foro_hub.foroHub.services.perfil;

import dev.foro_hub.foroHub.model.Perfil;

public record ListadoPerfil(Long id, String nombre) {
    public ListadoPerfil(Perfil perfil){
        this(perfil.getId(), perfil.getNombre());
    }
}
