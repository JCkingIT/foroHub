package dev.foro_hub.foroHub.model;

import dev.foro_hub.foroHub.services.usuario.ActualizarUsuario;
import dev.foro_hub.foroHub.services.usuario.RegistrarUsuario;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String clave;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
        private Perfil perfil;

    public Usuario(RegistrarUsuario registrarUsuario, Perfil perfil){
        this.nombre = registrarUsuario.nombre();
        this.email = registrarUsuario.email();
        this.clave = registrarUsuario.clave();
        this.status = true;
        this.perfil = perfil;
    }
    public void actualizar(ActualizarUsuario actualizarUsuario){
        if(actualizarUsuario.nombre() != null) this.nombre = actualizarUsuario.nombre();
        if(actualizarUsuario.clave() != null) this.clave = actualizarUsuario.clave();
    }

    public void eliminar(){
        this.status = false;
    }

}
