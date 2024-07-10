package dev.foro_hub.foroHub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Data
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String clave;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfilId")
    private Perfil perfil;

}
