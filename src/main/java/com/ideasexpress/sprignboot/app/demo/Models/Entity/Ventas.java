package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;


@Entity

@Table(name = "ventas")
public class Ventas { 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO  )
    @Column(name="id_venta")
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha")
    private Date fecha;
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name="subtotal")
    private int subtotal;
    @Column(name="total")
    private int total;

    @OneToMany(mappedBy="ventaMap", cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Detalle> detalle;

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void detallesDeVenta(Detalle detalles){
        if(detalle==null) detalle = new  ArrayList<>();
        detalle.add(detalles);

        detalles.setVenta(this);
    }
    public Ventas() {
}
    public Ventas(Date fecha, int subtotal, int total) {
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.total = total;
    }
        
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", subtotal='" + getSubtotal() + "'" +
            ", total='" + getTotal() + "'" +
            ", detalle='" + getDetalle() + "'" +
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
}
