package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity

@Table(name = "ventas")
public class Ventas { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long id;
    @Column(name="subtotal")
    private int subtotal;
    @Column(name="total")
    private int total;
    @Column(name="id_Usuario")
    private Long id_Usuario;

    @OneToMany(mappedBy="venta", cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Detalle> detalle;

    public void detallesDeVenta(Detalle detalles){
        if(detalle==null) detalle = new  ArrayList<>();
        detalle.add(detalles);

        detalles.setVenta(this);
    }



    public Ventas() {
        
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", subtotal='" + getSubtotal() + "'" +
            ", total='" + getTotal() + "'" +
            ", id_Usuario='" + getId_Usuario() + "'" +
            "}";
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getId_Usuario() {
        return this.id_Usuario;
    }

    public void setId_Usuario(Long id_Usuario) {
        this.id_Usuario = id_Usuario;
    }



    
}
