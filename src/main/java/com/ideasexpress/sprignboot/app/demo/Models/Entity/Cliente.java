package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    // Atributos
    //@NotNull(message = "Debes especificar el id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Long Id;

    @NotEmpty(message = "Debes especificar el Usuario")
    private String usuario;

    @NotEmpty(message = "Debes especificar el Nombre")
    private String Nombre;
    @NotEmpty (message = "Debes especificar el Apellido")
    @Size(message = "El apellido deber medir entre 1 y 50")
    private String Apellido;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull (message = "campo requerido")
    private Date CreateAt;
    @Email
    @NotEmpty(message = "Debes rellenar este campo")
    private String Email;

    @NotEmpty(message = "Debes rellenar este campo")
    private String Pass;
    @NotEmpty(message = "Debes rellenar este campo")
    private String Roles;

    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Ventas> ventaCliente;

    //llena datos
    public void ventasPorCliente(Ventas venta){
        if(ventaCliente==null) ventaCliente = new ArrayList<>();
        
        ventaCliente.add(venta);
        venta.setCliente(this);

    }
    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String password) {
        Pass = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String user) {
        usuario = user;
    }

    

    // @PrePersist
    // public void PrePersist(){
    // CreateAt = new Date();
    // }
    
    


    
    
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public Cliente() {
    }

    public Cliente(String usuario, String Nombre, String Apellido, String Email, String Pass, String Roles, Date CreateAt) {
        this.usuario = usuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Email = Email;
        this.Pass = Pass;
        this.Roles = Roles;
        this.CreateAt = CreateAt;
    }

    public List<Ventas> getVentaCliente() {
        return this.ventaCliente;
    }

    public void setVentaCliente(List<Ventas> ventaCliente) {
        this.ventaCliente = ventaCliente;
    }

    public Cliente Id(Long Id) {
        setId(Id);
        return this;
    }

    public Cliente usuario(String usuario) {
        setUsuario(usuario);
        return this;
    }

    public Cliente Nombre(String Nombre) {
        setNombre(Nombre);
        return this;
    }

    public Cliente Apellido(String Apellido) {
        setApellido(Apellido);
        return this;
    }

    public Cliente Email(String Email) {
        setEmail(Email);
        return this;
    }

    public Cliente Pass(String Pass) {
        setPass(Pass);
        return this;
    }

    public Cliente Roles(String Roles) {
        setRoles(Roles);
        return this;
    }

    public Cliente CreateAt(Date CreateAt) {
        setCreateAt(CreateAt);
        return this;
    }

    public Cliente ventaCliente(List<Ventas> ventaCliente) {
        setVentaCliente(ventaCliente);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", Nombre='" + getNombre() + "'" +
            ", Apellido='" + getApellido() + "'" +
            ", Email='" + getEmail() + "'" +
            ", Pass='" + getPass() + "'" +
            ", Roles='" + getRoles() + "'" +
            ", CreateAt='" + getCreateAt() + "'" +
            ", ventaCliente='" + getVentaCliente() + "'" +
            "}";
    }

}
