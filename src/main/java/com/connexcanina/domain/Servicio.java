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

    @Column(name = "nombre_servicio")
    private String nombreServicio;

    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    private String imagen;

    public Servicio() {
    }

    public Servicio(String nombreServicio, String descripcion, BigDecimal precio, String imagen) {
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }
}
