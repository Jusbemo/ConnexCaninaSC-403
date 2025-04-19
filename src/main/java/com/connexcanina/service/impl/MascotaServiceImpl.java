package com.connexcanina.service.impl;

import com.connexcanina.dao.MascotaDao;
import com.connexcanina.domain.Mascota;
import com.connexcanina.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaDao mascotaDao;

    @Override
    public List<Mascota> getMascotas() {
        return mascotaDao.findAll();
    }

    @Override
    public Mascota getMascota(Mascota mascota) {
        return mascotaDao.findById(mascota.getIdMascota()).orElse(null);
    }

    @Override
    public void save(Mascota mascota) {
        mascotaDao.save(mascota);
    }

    @Override
    public void delete(Mascota mascota) {
        mascotaDao.delete(mascota);
    }

    @Override
    public void eliminarMascotaPorId(Long id) {
        mascotaDao.deleteById(id);
    }
}
