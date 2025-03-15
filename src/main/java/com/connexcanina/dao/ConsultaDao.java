package com.connexcanina.dao;

import com.connexcanina.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaDao extends JpaRepository<Consulta,Long> {
}
