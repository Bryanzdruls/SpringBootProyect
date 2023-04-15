package com.ideasexpress.sprignboot.app.demo.Service;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideasexpress.sprignboot.app.demo.Models.DAO.ClienteDaoImp;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.ProductoDaoImp;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.VentasDaoImp;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Detalle;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Producto;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Ventas;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class Insercion {
    @Autowired
    private final ClienteDaoImp clienteDaoImp;
    @Autowired
    private final VentasDaoImp ventasDaoImp;
    @Autowired
    private final ProductoDaoImp productoDaoImp;
    
    @Transactional
    public void ventasPorCliente(){
        Cliente cliente1=new Cliente("bronx", "brian",
         "torres", "eltiobryanz@gmail.com", 
         "123", "ROle_admin", new Date());    
        Cliente cliente2=new Cliente("juanJO", "gonsales",
         "gonsalez", "gonsales@gmail.com", 
         "123", "ROle_admin", new Date());       
        clienteDaoImp.save(cliente1);
        clienteDaoImp.save(cliente2);


        //se decalran las ventas
        Ventas venta1=new Ventas(new Date(),2000,2000);       
        Ventas venta2=new Ventas(new Date(),20,20);
        Ventas venta3=new Ventas(new Date(),30,30);
        Ventas venta4=new Ventas(new Date(),500,500);
        //se guardan las ventas
        ventasDaoImp.save(venta1);
        ventasDaoImp.save(venta2);
        ventasDaoImp.save(venta3);
        ventasDaoImp.save(venta4);

        //se relacionan las ventas
        cliente1.ventasPorCliente(venta1);

        cliente2.ventasPorCliente(venta2);
        cliente2.ventasPorCliente(venta3);
        cliente2.ventasPorCliente(venta4);
        //cliente1.ventaCliente(venta1);

        //Se crean los productos
        Producto producto1 = new Producto(100, 0, "Bicileta", "Fea");
        Producto producto2 = new Producto(100, 6, "Bicileta", "linda");
        Producto producto3 = new Producto(100, 90, "Bicileta", "muy linda");
        Producto producto4 = new Producto(100, 90, "Especial", "muy linda");

        //se guardan los productos en la base de datos
        productoDaoImp.save(producto1);
        productoDaoImp.save(producto2);
        productoDaoImp.save(producto3);
        productoDaoImp.save(producto4);
        //se crea el detalle
        Detalle detalle1 = new Detalle(producto1.getUnidades(),producto1.getPrecio()*producto1.getUnidades(), producto1);
        Detalle detalle2 = new Detalle(producto2.getUnidades(),producto2.getPrecio()*producto2.getUnidades(),producto2);
        Detalle detalle3 = new Detalle(producto3.getUnidades(),producto3.getPrecio()*producto3.getUnidades(),producto3);
        Detalle detalle4 = new Detalle(producto4.getUnidades(),producto4.getPrecio()*producto4.getUnidades(),producto4);

        //Se relaciona el detalle con la venta
        venta2.detallesDeVenta(detalle1);
        venta2.detallesDeVenta(detalle2);
        venta2.detallesDeVenta(detalle3);

        venta1.detallesDeVenta(detalle2);
        venta1.detallesDeVenta(detalle4);
        
    }
    
    public void detallesPorVenta(){
        
    }
       
    
}
