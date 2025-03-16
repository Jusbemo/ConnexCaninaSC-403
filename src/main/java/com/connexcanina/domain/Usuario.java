package com.connexcanina.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol idRol;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "correo_electronico", nullable = false, length = 100)
    private String correoElectronico;

    @Lob
    @Column(name = "direccion")
    private String direccion;

    @OneToMany(mappedBy = "idUsuario")
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "idUsuario")
    private List<Consulta> consultas = new ArrayList<>();

    @OneToMany(mappedBy = "idUsuario")
    private List<Mascota> mascotas = new ArrayList<>();

}