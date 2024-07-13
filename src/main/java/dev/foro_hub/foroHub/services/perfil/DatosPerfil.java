package dev.foro_hub.foroHub.services.perfil;

import dev.foro_hub.foroHub.model.Perfil;

public record DatosPerfil(String nombre) {
    public DatosPerfil(Perfil perfil){
        this(perfil.getNombre());
    }
}
