package com.ideasexpress.sprignboot.app.demo.Models.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente,Long> {

    Optional<Cliente> findByUsuario(String username);
    
}
