package com.ideasexpress.sprignboot.app.demo.Models.DAO;

import java.util.List;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Producto;

public interface IProductoDao {
    public List<Producto> findAll();
    public void save(Producto producto);
    public Producto findOne(Long id);
    public void delete(Long id);
}
