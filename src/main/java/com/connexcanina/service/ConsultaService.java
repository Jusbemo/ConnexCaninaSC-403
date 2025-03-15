package com.connexcanina.service;

import com.connexcanina.domain.Consulta;

import java.util.List;

public interface ConsultaService {
    public List<Consulta> getConsultas();

    public Consulta getConsulta(Consulta consulta);

    public void save(Consulta consulta);

    public void delete(Consulta consulta);
}

