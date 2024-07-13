package dev.foro_hub.foroHub.model;

import dev.foro_hub.foroHub.services.topico.ActualizarTopico;
import dev.foro_hub.foroHub.services.topico.RegistrarTopico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy="topico",  fetch = FetchType.LAZY)
    List<Respuesta> respuestas;

    public Topico(RegistrarTopico registrarTopico, Usuario usuario, Curso curso){
        this.titulo = registrarTopico.titulo();
        this.mensaje = registrarTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizar (ActualizarTopico actualizarTopico){
        if(actualizarTopico.titulo() != null) this.titulo = actualizarTopico.titulo();
        if(actualizarTopico.mensaje() != null) this.mensaje = actualizarTopico.mensaje();
    }

    public void eliminar(){
        this.status = false;
    }
}
