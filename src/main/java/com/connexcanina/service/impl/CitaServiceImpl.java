package com.connexcanina.service.impl;

import com.connexcanina.dao.CitaDao;
import com.connexcanina.dao.EspacioCitaDao;
import com.connexcanina.domain.Cita;
import com.connexcanina.domain.EspacioCita;
import com.connexcanina.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaDao citaDao;

    @Autowired
    EspacioCitaDao espacioCitaDao;

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
        System.out.println("Guardando cita con ID: " + cita.getIdCita());
        if (cita.getIdCita() > 0 && citaDao.existsById(cita.getIdCita())) {
            citaDao.save(cita);
        } else {
            throw new IllegalArgumentException("No se puede guardar una cita sin ID válido");
        }
    }

    @Transactional
    @Override
    public void delete(Cita cita) {
        EspacioCita espacio = cita.getIdEspacio();

        if (espacio != null) {
            cita.setIdEspacio(null);
            espacio.setCita(null);
            espacio.setEstado("disponible");

            // Guarda ambas entidades actualizadas.
            espacioCitaDao.save(espacio);
            citaDao.save(cita);
        }

        citaDao.delete(cita);
    }


    @Override
    public Optional<Cita> getCitaById(long id) {
        return citaDao.findById(id);
}

}
