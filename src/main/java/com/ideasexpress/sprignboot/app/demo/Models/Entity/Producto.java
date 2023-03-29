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
@Table(name = "productos")//Nombre Tablas

public class Producto implements Serializable{
    //Atributos
    @NotNull
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @Min(0)
    private int precio, unidades;
    @NotBlank
    private String nombre,descripcion;
    
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

    //@Column(name =)
    
    
}