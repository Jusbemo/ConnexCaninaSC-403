package com.connexcanina.service.impl;

import com.connexcanina.dao.RolDao;
import com.connexcanina.domain.Rol;
import com.connexcanina.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolDao rolDao;

    @Override
    public List<Rol> getRoles() {
        return rolDao.findAll();
    }

    @Override
    public Rol getRol(Rol rol) {
        return rolDao.findById(rol.getIdRol()).orElse(null);
    }

    @Override
    public void save(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    public void delete(Rol rol) {
        rolDao.delete(rol);
    }
}
