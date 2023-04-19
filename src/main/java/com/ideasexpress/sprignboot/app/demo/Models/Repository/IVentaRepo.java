package com.ideasexpress.sprignboot.app.demo.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Ventas;

public interface IVentaRepo extends JpaRepository<Ventas,Long>{
    
}
