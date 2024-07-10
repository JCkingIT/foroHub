package dev.foro_hub.foroHub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Data
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topicoId")
    private Topico topico;
}
