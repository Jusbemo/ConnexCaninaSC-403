package com.connexcanina.service;

import com.connexcanina.domain.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    public List<Cita> getCitas();

    public Cita getCita(Cita cita);

    public void save(Cita cita);

    public void delete(Cita cita);

    public Optional<Cita> getCitaById(long id);
}
