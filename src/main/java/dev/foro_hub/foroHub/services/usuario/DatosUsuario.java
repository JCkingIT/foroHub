package dev.foro_hub.foroHub.services.usuario;

import dev.foro_hub.foroHub.model.Usuario;

public record DatosUsuario(String autor) {
    public DatosUsuario(Usuario usuario){
        this(usuario.getNombre());
    }
}
