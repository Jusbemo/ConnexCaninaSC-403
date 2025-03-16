package com.connexcanina.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "espacio_cita")
public class EspacioCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espacio", nullable = false)
    private Integer id;

    @Column(name = "fecha_hora_disponible", nullable = false)
    private Instant fechaHoraDisponible;

    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;

}