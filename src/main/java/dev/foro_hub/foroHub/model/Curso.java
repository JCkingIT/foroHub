package dev.foro_hub.foroHub.model;

import dev.foro_hub.foroHub.services.curso.ActualizarCurso;
import dev.foro_hub.foroHub.services.curso.RegistrarCurso;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "curso")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private Boolean status;

    public Curso(RegistrarCurso registrarCurso) {
        this.nombre = registrarCurso.nombre();
        this.categoria = registrarCurso.categoria();
        this.status = true;
    }

    public void actualizar(ActualizarCurso actualizarCurso){
        if(actualizarCurso.nombre() != null) this.nombre = actualizarCurso.nombre();
        if(actualizarCurso.categoria() != null) this.categoria = actualizarCurso.categoria();
    }

    public void eliminar(){
        this.status = false;
    }
}

