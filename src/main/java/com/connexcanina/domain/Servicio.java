package com.connexcanina.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Table (name = "Servicio")
@Entity
public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private long idServicio;

    @Column(name = "descripcion_corta")
    private String descripcion;

    @Column(name = "descripcion_larga")
    private String descripcionLarga;

    @Column(name = "nombre_servicio")
    private String nombreServicio;


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    private String imagen;

    public Servicio() {
    }

    public Servicio(long idServicio, String nombreServicio, String descripcion, String descripcionLarga, BigDecimal precio, String imagen) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.precio = precio;
        this.imagen = imagen;
    }
}
