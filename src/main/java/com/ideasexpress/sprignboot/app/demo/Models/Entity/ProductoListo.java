package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="productolisto")
public class ProductoListo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id_Producto;

    public Long getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(Long id_Producto) {
        Id_Producto = id_Producto;
    }

    private String Nombre;
    private String Descripcion;
    private int Cantidad;
    private int Precio;
    private int codigo;

    @ManyToOne
    @JoinColumn
    private Ventas venta;

    public ProductoListo(String nombre, String descripcion, int float1, int precio, int codigo, Ventas venta) {
        Nombre = nombre;
        Descripcion = descripcion;
        Cantidad = float1;
        Precio = precio;
        this.codigo = codigo;
        this.venta = venta;
    }

    public ProductoListo() {
    }

    public ProductoListo(Long id_product, String nombre2, String descripcion2, Float cantidad2, Float precio2,
            String codigo2, Ventas v) {
    }

    public ProductoListo(Float cantidad2, Float precio2, String nombre2, String codigo2, Ventas v) {
    }

    public int getTotal() {
        return this.Cantidad * this.Precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    } 

    @Override
    public String toString() {
        return "{" +
            " Id_Producto='" + getId_Producto() + "'" +
            ", Nombre='" + getNombre() + "'" +
            ", Descripcion='" + getDescripcion() + "'" +
            ", Cantidad='" + getCantidad() + "'" +
            ", Precio='" + getPrecio() + "'" +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }


}
