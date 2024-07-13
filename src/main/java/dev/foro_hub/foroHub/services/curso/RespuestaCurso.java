package dev.foro_hub.foroHub.services.curso;

import dev.foro_hub.foroHub.model.Curso;

public record RespuestaCurso(Long id, String nombre, String categoria) {
    public RespuestaCurso(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
