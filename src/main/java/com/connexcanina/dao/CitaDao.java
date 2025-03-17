package com.connexcanina.dao;

import com.connexcanina.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaDao extends JpaRepository<Cita, Long> {
}
