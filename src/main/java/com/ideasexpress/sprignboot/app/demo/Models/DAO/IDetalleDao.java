package com.ideasexpress.sprignboot.app.demo.Models.DAO;

import java.util.List;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Detalle;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Producto;


public interface IDetalleDao {
    public List<Detalle> findAll();
    public void save(Detalle detalle);
    public Detalle findOne(Long id);
    public void delete(Long id);
    public List<Detalle> findVentas(Long id);

    public void unirProd(Producto producto);
}
