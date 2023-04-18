package com.ideasexpress.sprignboot.app.demo.Service;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideasexpress.sprignboot.app.demo.Models.DAO.ClienteDaoImp;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.DetalleDaoImp;
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
    @Autowired
    private final DetalleDaoImp detalleDaoImp;
    
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
        Ventas venta1=new Ventas(new Date(),0,0);       
        Ventas venta2=new Ventas(new Date(),0,0);
        Ventas venta3=new Ventas(new Date(),0,0);
        Ventas venta4=new Ventas(new Date(),0,0);
        //se guardan las ventas
        ventasDaoImp.save(venta1);
        ventasDaoImp.save(venta2);
        ventasDaoImp.save(venta3);
        ventasDaoImp.save(venta4);

        //se relacionan las ventas
        cliente1.ventasPorCliente(venta1);
        cliente1.ventasPorCliente(venta2);

        cliente2.ventasPorCliente(venta3);
        cliente2.ventasPorCliente(venta4);
        //cliente1.ventaCliente(venta1);

        //Se crean los productos
        Producto producto1 = new Producto(100, 10, "Bicileta", "Fea");
        Producto producto2 = new Producto(100, 6, "Bicileta", "linda");
        Producto producto3 = new Producto(100, 90, "Bicileta", "muy linda");
        Producto producto4 = new Producto(100, 80, "Especial", "muy linda");

        Producto producto5 = new Producto(100, 80, "Prueba", "Form");

        //se guardan los productos en la base de datos
        productoDaoImp.save(producto1);
        productoDaoImp.save(producto2); 
        productoDaoImp.save(producto3);
        productoDaoImp.save(producto4);
        //se crea el detalle
        Detalle detalle1 = new Detalle(1,producto1.getPrecio()*producto1.getUnidades(),producto1);
        Detalle detalle2 = new Detalle(2,producto2.getPrecio()*producto2.getUnidades(),producto2);
        Detalle detalle3 = new Detalle(3,producto3.getPrecio()*producto3.getUnidades(),producto3);
        Detalle detalle4 = new Detalle(4,producto4.getPrecio()*producto4.getUnidades(),producto4);
        Detalle detalle5 = new Detalle(5,producto3.getPrecio()*producto3.getUnidades(),producto3);
        Detalle detalle6 = new Detalle(6,producto4.getPrecio()*producto4.getUnidades(),producto4);
        Detalle detalle7 = new Detalle(7,producto1.getPrecio()*producto1.getUnidades(),producto1);
        Detalle detalle8 = new Detalle(8,producto2.getPrecio()*producto1.getUnidades(),producto2);




        /*Detalle detalle1 = new Detalle(0,0, producto1);
        Detalle detalle2 = new Detalle(0,0,producto2);
        Detalle detalle3 = new Detalle(0,0,producto3);
        Detalle detalle4 = new Detalle(0,0,producto4);*/
        
        detalleDaoImp.save(detalle1);
        detalleDaoImp.save(detalle2);
        detalleDaoImp.save(detalle3);
        detalleDaoImp.save(detalle4);
        detalleDaoImp.save(detalle5);
        detalleDaoImp.save(detalle6);
        //Se relaciona el detalle con la venta
        
        //NO PUEDEN TENER EL MISMO DETALLE 2 ventas
        venta1.detallesDeVenta(detalle1);
        venta1.detallesDeVenta(detalle2);

        venta2.detallesDeVenta(detalle3);
        venta2.detallesDeVenta(detalle4);
        
        venta3.detallesDeVenta(detalle5);
        venta3.detallesDeVenta(detalle6);

        venta4.detallesDeVenta(detalle7);
        venta4.detallesDeVenta(detalle8);



        //venta4.detallesDeVenta(detalle6);

        //venta2.setSubtotal(detalle1.getValorVenta()+detalle2.getValorVenta()+detalle3.getValorVenta());
        
        
    }
       
    
}
