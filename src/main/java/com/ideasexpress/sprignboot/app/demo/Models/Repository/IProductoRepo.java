package com.ideasexpress.sprignboot.app.demo.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Producto;

public interface IProductoRepo extends JpaRepository<Producto,Long>{
    Producto findByid(Long id);
}
