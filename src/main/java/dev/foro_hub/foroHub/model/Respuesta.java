package dev.foro_hub.foroHub.model;

import dev.foro_hub.foroHub.services.respuesta.ActualizarRespuesta;
import dev.foro_hub.foroHub.services.respuesta.RegistrarRespuesta;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private Boolean solucion;
    private Boolean status;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public Respuesta(RegistrarRespuesta registrarRespuesta, Usuario usuario, Topico topico){
        this.mensaje = registrarRespuesta.mensaje();
        this.usuario = usuario;
        this.topico = topico;
        this.solucion = false;
        this.status = true;
    }

    public void actualizar(ActualizarRespuesta actualizarRespuesta){
        if(actualizarRespuesta.mensaje() != null) this.mensaje = actualizarRespuesta.mensaje();
        if(actualizarRespuesta.solucion() != null) this.solucion = actualizarRespuesta.solucion();
    }

    public void eliminar(){
        this.status = false;
    }
}
