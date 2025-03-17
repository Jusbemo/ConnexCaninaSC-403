package com.connexcanina.service.impl;

import com.connexcanina.dao.CitaDao;
import com.connexcanina.domain.Cita;
import com.connexcanina.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaDao citaDao;

    @Override
    public List<Cita> getCitas() {
        return citaDao.findAll();
    }

    @Override
    public Cita getCita(Cita cita) {
        return citaDao.findById(cita.getIdCita()).orElse(null);
    }

    @Override
    public void save(Cita cita) {
        citaDao.save(cita);
    }

    @Override
    public void delete(Cita cita) {
        citaDao.delete(cita);
    }

    @Override
    public Optional<Cita> getCitaById(long id) {
        return citaDao.findById(id);
}

}
