package com.connexcanina.service;

import com.connexcanina.domain.Rol;

import java.util.List;

public interface RolService {
    public List<Rol> getRoles();

    public Rol getRol(Rol rol);

    public void save(Rol rol);

    public void delete(Rol rol);
}
