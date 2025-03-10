package com.connexcanina.service;

import com.connexcanina.domain.Categoria;
import java.util.List;

public interface CategoriaService {

    // Se obtiene un listado de categorias en un List
    public List<Categoria> getCategorias(boolean activos);

    // Se obtiene un Categoria, a partir del id de un categoria
    public Categoria getCategoria(Categoria categoria);

    // Se inserta una nueva categoria si el id de la categoria esta vacío
    // Se actualiza una categoria si el id de la categoria NO esta vacío
    public void save(Categoria categoria);

    // Se elimina el categoria que tiene el id pasado por parámetro
    public void delete(Categoria categoria);


}