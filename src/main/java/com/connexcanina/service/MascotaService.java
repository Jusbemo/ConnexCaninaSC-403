package com.connexcanina.service;

import com.connexcanina.domain.Mascota;

import java.util.List;

public interface MascotaService {
    public List<Mascota> getMascotas();

    public Mascota getMascota(Mascota mascota);

    public void save(Mascota mascota);

    public void delete(Mascota mascota);

    public void eliminarMascotaPorId(Long id);
}
