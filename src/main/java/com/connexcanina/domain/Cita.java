package com.connexcanina.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita", nullable = false)
    private long idCita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private com.connexcanina.domain.Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mascota", nullable = false)
    private com.connexcanina.domain.Mascota idMascota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio idServicio;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;


    public String getFecha() {
        return fechaHora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getHora() {
        return fechaHora.atZone(ZoneId.of("America/Mexico_City")).format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public void setFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime nuevaFechaHora = LocalDateTime.of(LocalDateTime.parse(fecha, formatter).toLocalDate(),
                fechaHora.toLocalTime());
        this.fechaHora = nuevaFechaHora;
    }

    public void setHora(String hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime nuevaHora = LocalTime.parse(hora, formatter);
        this.fechaHora = LocalDateTime.of(fechaHora.toLocalDate(), nuevaHora);
    }

}