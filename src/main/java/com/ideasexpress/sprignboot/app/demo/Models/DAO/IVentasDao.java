package com.ideasexpress.sprignboot.app.demo.Models.DAO;

import java.util.List;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Detalle;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Ventas;

public interface IVentasDao {
    public List<Ventas> findAll();
    public void save(Ventas venta);
    public Ventas findOne(Long id);
    public void delete(Long id);
    public List<Ventas> findOneCliente(Long id);

    public int totalFactura(List<Ventas>ventas);

    public List<Integer> subTotalList(List<Ventas> ventas,List<Detalle> detalle);

}
