package dev.foro_hub.foroHub.controller;

import dev.foro_hub.foroHub.model.Usuario;
import dev.foro_hub.foroHub.services.usuario.IUsuarioRepository;
import dev.foro_hub.foroHub.services.usuario.RegistrarUsusario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrar")
@AllArgsConstructor
public class RegistrerController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario registrarUsuario(@RequestBody @Valid RegistrarUsusario registrarUsusario) {
        //PasswordEncoder passwordEncoder;
        Usuario usuario = new Usuario(registrarUsusario.nombre(), registrarUsusario.email(), passwordEncoder.encode(registrarUsusario.clave()));

        usuario = usuarioRepository.save(usuario);
        return usuario;
    }
}