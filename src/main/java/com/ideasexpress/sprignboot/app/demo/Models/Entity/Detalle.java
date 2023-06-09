package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle")
public class Detalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle")
    public Long id;
    @Column(name="cantidad")
    public int cantidad;
    @Column(name="valorVenta")
    public int valorVenta;


    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="Venta_id")
    private Ventas ventaMap;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="producto_id")
    private Producto productoMap;

    //metodos

    public Producto getProductoMap() {
        return productoMap;
    }

    public void setProductoMap(Producto productoMap) {
        this.productoMap = productoMap;
    }

    public Ventas getVentaMap() {
        return this.ventaMap;
    }

    public void setVentaMap(Ventas ventaMap) {
        this.ventaMap = ventaMap;
    }

    public Detalle(){
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getCantidad() {
        return cantidad;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public int getValorVenta() {
        return valorVenta;
    }


    public void setValorVenta(int valorVenta) {
        this.valorVenta = valorVenta;
    }




    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", cantidad='" + getCantidad() + "'" +
            ", valorVenta='" + getValorVenta() + "'" +
            ", producto='" + getProductoMap() + "'" +
            "}";
    }
    
    public Ventas getVenta() {
        return ventaMap;
    }

    public void setVenta(Ventas venta) {
        this.ventaMap = venta;
    }
    public Detalle(int cantidad, int valorVenta, Producto productoMap) {

        this.cantidad = cantidad;
        this.valorVenta = valorVenta;
        this.productoMap=productoMap;
    }
    public Detalle(Long id,int cantidad, int valorVenta, Producto productoMap) {
        this.id = id;
        this.cantidad = cantidad;
        this.valorVenta = valorVenta;
        this.productoMap=productoMap;
    }
    public void aumentarCantidad() {
        this.cantidad++;
    }

    public Detalle(Long id, int cantidad, int valorVenta,  Producto productoMap ,Ventas ventaMap) {
        this.id = id;
        this.cantidad = cantidad;
        this.valorVenta = valorVenta;
        this.ventaMap = ventaMap;
        this.productoMap = productoMap;
    }

    public Detalle(int cantidad, int valorVenta,  Producto productoMap ,Ventas ventaMap) {

        this.cantidad = cantidad;
        this.valorVenta = valorVenta;
        this.ventaMap = ventaMap;
        this.productoMap = productoMap;
    }
    public int precioTotal(){
        return this.getCantidad()*this.getProductoMap().getPrecio();
    }
}

    
