package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")//Nombre Tablas
public class Producto implements Serializable{
    //Atributos
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id, precio, unidades;
    private String nombre,descripion; 
    //@Column(name =)

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getUnidades() {
        return this.unidades;
    }

    public void setUnidades(Long unidades) {
        this.unidades = unidades;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripion() {
        return this.descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }
    
}
