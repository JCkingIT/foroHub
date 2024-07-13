package dev.foro_hub.foroHub.services.usuario;

import dev.foro_hub.foroHub.model.Usuario;
import dev.foro_hub.foroHub.services.perfil.DatosPerfil;

public record DatosUsuario(String nombre, String perfil) {
    public DatosUsuario(Usuario usuario){
        this(usuario.getNombre(), usuario.getPerfil().getNombre());
    }
}
