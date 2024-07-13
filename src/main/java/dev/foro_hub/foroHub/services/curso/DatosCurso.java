package dev.foro_hub.foroHub.services.curso;

import dev.foro_hub.foroHub.model.Curso;

public record DatosCurso(String nombre, String categoria) {
    public DatosCurso(Curso curso){
        this(curso.getNombre(), curso.getCategoria());
    }
}
