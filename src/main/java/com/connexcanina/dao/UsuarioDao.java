package com.connexcanina.dao;

import com.connexcanina.domain.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = {"idRol"})
    Usuario findByUsername(String username);

    @EntityGraph(attributePaths = {"citas"})
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario = :id")
    Usuario findWithCitas(Long id);

    @EntityGraph(attributePaths = {"consultas"})
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario = :id")
    Usuario findWithConsultas(Long id);

    @EntityGraph(attributePaths = {"mascotas"})
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario = :id")
    Usuario findWithMascotas(Long id);
}

