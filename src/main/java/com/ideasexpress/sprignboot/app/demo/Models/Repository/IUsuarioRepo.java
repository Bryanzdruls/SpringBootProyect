package com.ideasexpress.sprignboot.app.demo.Models.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario,Long> {
    
    Usuario findByNombre(String nombre);
}
