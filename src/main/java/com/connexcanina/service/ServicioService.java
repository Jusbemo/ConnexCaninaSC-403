package com.connexcanina.service;

import com.connexcanina.domain.Servicio;

import java.util.List;

public interface ServicioService {
    public List<Servicio> getServicios();

    public Servicio getServicio(Servicio servicio);

    public void save(Servicio servicio);

    public void delete(Servicio servicio);

    public List<Servicio> getTop3Servicios();
}
