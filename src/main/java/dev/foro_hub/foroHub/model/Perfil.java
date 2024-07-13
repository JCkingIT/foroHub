package dev.foro_hub.foroHub.model;

import dev.foro_hub.foroHub.services.perfil.ActualizarPerfil;
import dev.foro_hub.foroHub.services.perfil.RegistrarPerfil;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "perfil")
@Entity(name = "Perfil")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean status;

    public Perfil(RegistrarPerfil registrarPerfil){
        this.nombre = registrarPerfil.nombre();
        this.status = true;
    }

    public void actualizar(ActualizarPerfil actualizarPerfil){
        if(actualizarPerfil.nombre() != null) this.nombre = actualizarPerfil.nombre();
    }

    public void eliminar(){
        this.status = false;
    }
}
