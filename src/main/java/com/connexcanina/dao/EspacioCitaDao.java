package com.connexcanina.dao;

import com.connexcanina.domain.EspacioCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EspacioCitaDao extends JpaRepository<EspacioCita, Long> {
    boolean existsByFechaHoraDisponible(LocalDateTime fechaHoraDisponible);
    EspacioCita findByFechaHoraDisponible(LocalDateTime fechaHoraDisponible);

}
