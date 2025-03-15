package com.connexcanina.service.impl;

import com.connexcanina.dao.ConsultaDao;
import com.connexcanina.domain.Consulta;
import com.connexcanina.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaDao consultaDao;

    @Override
    public List<Consulta> getConsultas() {
        var lista = consultaDao.findAll();
        return lista;
    }

    @Override
    public Consulta getConsulta(Consulta consulta) {
        return consultaDao.findById(consulta.getIdConsulta()).orElse(null);
    }

    @Override
    public void save(Consulta consulta) {
        consultaDao.save(consulta);
    }

    @Override
    public void delete(Consulta consulta) {
        consultaDao.delete(consulta);
    }
}
