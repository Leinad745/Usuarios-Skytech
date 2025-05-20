package com.ServicioJuegos.SkyTech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "juego")
@Data // Genera automáticamente los métodos getter y setter, así como equals(), hashCode() y toString()
@NoArgsConstructor
@AllArgsConstructor
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJuego;

    @Column(unique=true, nullable = false)
    private String tituloJuego;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Date fechaLanzamiento;

    @Column(nullable = false)
    private String desarrollador;

    @Column(nullable = false)
    private String publicador;

    @Column(nullable = false, length = 1)
    private String clasificacionESRB;

    @Column(nullable = false)
    private boolean disponibilidad;
}
