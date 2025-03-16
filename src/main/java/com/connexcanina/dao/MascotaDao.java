package com.connexcanina.dao;

import com.connexcanina.domain.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaDao extends JpaRepository<Mascota, Long> {
}
