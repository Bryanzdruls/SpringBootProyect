package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "productos") // Nombre Tablas

public class Producto implements Serializable {
    
    // Atributos
    @Id
    // @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message="ingrese un numero mayor a 0")
    @Min(value=30)
    private int precio;
    @NotNull(message = "ingrese un numero")
    private int unidades;
    @NotBlank(message="Ingrese el nombre del producto")
    private String nombre;
    @NotBlank(message = "Ingrese la descripcion del producto")
    private String descripcion;

    public Producto(){
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Producto(int precio, int unidades, String nombre, String descripcion,Long id) {
        this.precio = precio;
        this.unidades = unidades;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id=id;
    }
    public Producto(int precio, int unidades, String nombre, String descripcion) {
        this.precio = precio;
        this.unidades = unidades;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Producto(Long id, int precio, int unidades, String nombre, String descripcion) {
        this.id = id;
        this.precio = precio;
        this.unidades = unidades;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Producto id(Long id) {
        setId(id);
        return this;
    }

    public Producto precio(int precio) {
        setPrecio(precio);
        return this;
    }

    public Producto unidades(int unidades) {
        setUnidades(unidades);
        return this;
    }

    public Producto nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Producto descripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", unidades='" + getUnidades() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
    public boolean sinExistencia() {
        return this.unidades <= 0;
    }
    public void restarUnidades(int res) {
        this.unidades -= res;
    }


}