package com.connexcanina.service;

import com.connexcanina.domain.EspacioCita;

import java.util.List;

public interface EspacioCitaService {
    public List<EspacioCita> getEspaciosCitas();

    public EspacioCita getEspacioCita(EspacioCita espacioCita);

    public void save(EspacioCita espacioCita);

    public void delete(EspacioCita espacioCita);

    public EspacioCita getEspacioCitaById(long idEspacio);
}
