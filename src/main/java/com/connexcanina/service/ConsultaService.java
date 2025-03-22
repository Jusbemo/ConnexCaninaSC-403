package com.connexcanina.service;

import com.connexcanina.domain.Cita;
import com.connexcanina.domain.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {
    public List<Consulta> getConsultas();

    public Consulta getConsulta(Consulta consulta);

    public void save(Consulta consulta);

    public void delete(Consulta consulta);

    public Optional<Consulta> getConsultaById(long id);
}

