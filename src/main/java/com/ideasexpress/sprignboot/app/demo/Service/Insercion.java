package com.ideasexpress.sprignboot.app.demo.Service;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideasexpress.sprignboot.app.demo.Models.DAO.ClienteDaoImp;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.VentasDaoImp;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;
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
    
    @Transactional
    public void ventasPorCliente(){
        Cliente cliente1=new Cliente("bronx", "brian",
         "torres", "eltiobryanz@gmail.com", 
         "123", "ROle_admin", new Date(200,4,5));    
        Cliente cliente2=new Cliente("juanJO", "gonsales",
         "gonsalez", "gonsales@gmail.com", 
         "123", "ROle_admin", new Date(200,4,5));       
        clienteDaoImp.save(cliente1);
        clienteDaoImp.save(cliente2);


        //se decalran las ventas
        Ventas venta1=new Ventas(new Date(0, 0, 0, 0, 0, 0),2000,2000);
        
        Ventas venta2=new Ventas(new Date(0, 0, 0, 0, 0, 0),20,20);
        Ventas venta3=new Ventas(new Date(0, 0, 0, 0, 0, 0),30,30);
        Ventas venta4=new Ventas(new Date(0, 0, 0, 0, 0, 0),500,500);
        //se guardan las ventas
        ventasDaoImp.save(venta1);
        ventasDaoImp.save(venta2);
        ventasDaoImp.save(venta3);
        ventasDaoImp.save(venta4);


        //se relacionan las ventas
        //cliente1.ventasPorCliente(venta1);
        cliente2.ventasPorCliente(venta2);
        cliente2.ventasPorCliente(venta3);
        cliente2.ventasPorCliente(venta4);
        //cliente1.ventaCliente(venta1);
        System.out.println(cliente2);
    }
    
}
