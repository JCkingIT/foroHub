package dev.foro_hub.foroHub.utilities.perfil;

import dev.foro_hub.foroHub.model.Curso;

public record ListadoCurso(Long id, String nombre, String categoria) {
    public ListadoCurso(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
