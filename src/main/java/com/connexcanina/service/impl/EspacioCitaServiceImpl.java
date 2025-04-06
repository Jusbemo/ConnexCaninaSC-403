package com.connexcanina.service.impl;

import com.connexcanina.dao.CitaDao;
import com.connexcanina.dao.EspacioCitaDao;
import com.connexcanina.domain.Cita;
import com.connexcanina.domain.EspacioCita;
import com.connexcanina.service.CitaService;
import com.connexcanina.service.EspacioCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspacioCitaServiceImpl implements EspacioCitaService {

    @Autowired
    private EspacioCitaDao espacioCitaDao;

    @Autowired
    CitaDao citaDao;

    @Override
    public List<EspacioCita> getEspaciosCitas() {
        return espacioCitaDao.findAll();
    }

    @Override
    public EspacioCita getEspacioCita(EspacioCita espacioCita) {
        return espacioCitaDao.findById(espacioCita.getIdEspacio()).orElse(null);
    }

    @Override
    public void save(EspacioCita espacioCita) {
        // solo validar si el nuevo espacio no está en estado cancelado
        if (!"cancelada".equalsIgnoreCase(espacioCita.getEstado())) {
            EspacioCita existente = espacioCitaDao.findByFechaHoraDisponible(espacioCita.getFechaHoraDisponible());

            if (existente != null && existente.getIdEspacio() != espacioCita.getIdEspacio()) {
                throw new IllegalArgumentException("Ya existe un espacio en esa fecha y hora");
            }
        }

        espacioCitaDao.save(espacioCita);
    }

    @Transactional
    @Override
    public void delete(EspacioCita espacioCita) {
        Cita cita = espacioCita.getCita();

        if (cita != null) {
            cita.setIdEspacio(null);          // ← fundamental
            citaDao.save(cita);               // ← guarda la cita actualizada (sin referencia)
            citaDao.delete(cita);             // ← ahora elimina la cita
        }

        espacioCitaDao.delete(espacioCita);
    }



    @Override
    public EspacioCita getEspacioCitaById(long idEspacio) {
        return espacioCitaDao.findById(idEspacio).orElse(null);
    }

}
