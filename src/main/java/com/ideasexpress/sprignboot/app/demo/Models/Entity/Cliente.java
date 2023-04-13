package com.ideasexpress.sprignboot.app.demo.Models.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
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
    @NotNull(message = "Debes especificar el apellido")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotEmpty(message = "Debes especificar el Usuario")
    private String usuario;
    @NotEmpty(message = "Debes especificar el Nombre")
    private String Nombre;
    @NotEmpty (message = "Debes especificar el Apellido")
    @Size(message = "El apellido deber medir entre 1 y 50")
    private String Apellido;

    @Email
    @NotEmpty(message = "Debes rellenar este campo")
    private String Email;
    @NotEmpty(message = "Debes rellenar este campo")
    private String Pass;
    @NotEmpty(message = "Debes rellenar este campo")
    private String Roles;

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

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @NotNull (message = "campo requerido")
    private Date CreateAt;

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

}
