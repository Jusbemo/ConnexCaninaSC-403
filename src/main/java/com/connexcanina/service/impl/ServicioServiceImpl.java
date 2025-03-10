package com.connexcanina.service.impl;

import com.connexcanina.dao.ServicioDao;
import com.connexcanina.domain.Servicio;
import com.connexcanina.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {
    @Autowired
    private ServicioDao servicioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> getServicios() {
        return servicioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio getServicio(Servicio servicio) {
        return servicioDao.findById(servicio.getIdServicio()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Servicio servicio) {
        servicioDao.save(servicio);
    }

    @Override
    @Transactional
    public void delete(Servicio servicio) {
        servicioDao.delete(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> getTop3Servicios() {
        return servicioDao.findTop3ByOrderByIdServicioAsc();
    }
}
