package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name="id_detalle")
    public Long id_producto;
    @Column(name="id_detalle")
    public Long id_venta;




    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="Venta_id")
    private Ventas venta;


   

    //metodos
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


    public Long getId_producto() {
        return id_producto;
    }


    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }


    public Long getId_venta() {
        return id_venta;
    }


    public void setId_venta(Long id_venta) {
        this.id_venta = id_venta;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", cantidad='" + getCantidad() + "'" +
            ", valorVenta='" + getValorVenta() + "'" +
            ", id_producto='" + getId_producto() + "'" +
            ", id_venta='" + getId_venta() + "'" +
            "}";
    }
    
    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }
}
