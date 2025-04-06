package com.connexcanina.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "espacio_cita")
public class EspacioCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espacio", nullable = false)
    private long idEspacio;

    @Column(name = "fecha_hora_disponible", nullable = false)
    private LocalDateTime fechaHoraDisponible;

    @Transient
    private LocalDate fecha;

    @Transient
    private LocalTime hora;

    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;

    @OneToOne(mappedBy = "idEspacio", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Cita cita;

    public LocalDate getFecha() {
        return fechaHoraDisponible != null ? fechaHoraDisponible.toLocalDate() : fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return fechaHoraDisponible != null ? fechaHoraDisponible.toLocalTime() : hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    // Before persisting, combine fecha and hora into fechaHoraDisponible
    @PrePersist
    @PreUpdate
    public void updateFechaHoraDisponible() {
        if (fecha != null && hora != null) {
            fechaHoraDisponible = LocalDateTime.of(fecha, hora);
        }
    }



}