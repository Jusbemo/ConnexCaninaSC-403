package com.connexcanina.dao;

import com.connexcanina.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioDao extends JpaRepository<Servicio, Long> {

    List<Servicio> findTop3ByOrderByIdServicioAsc();
}
