package dev.foro_hub.foroHub.services.usuario;

import dev.foro_hub.foroHub.model.Usuario;
import dev.foro_hub.foroHub.services.perfil.DatosPerfil;

public record ListadoUsuario(Long id, String nombre, String email, DatosPerfil perfil) {
    public ListadoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(),new DatosPerfil(usuario.getPerfil().getNombre()));
    }
}
