package com.connexcanina.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta", nullable = false)
    private long idConsulta;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "`telefono`", length = 15)
    private String telefono;

    @Column(name = "`correo_electronico`", nullable = false, length = 100)
    private String correoElectronico;

    @Lob
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

}