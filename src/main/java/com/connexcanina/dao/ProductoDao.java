package com.connexcanina.dao;

import com.connexcanina.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoDao extends JpaRepository <Producto,Long>{
    
}
