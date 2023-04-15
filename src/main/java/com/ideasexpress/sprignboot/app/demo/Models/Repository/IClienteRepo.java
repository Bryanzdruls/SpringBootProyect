package com.ideasexpress.sprignboot.app.demo.Models.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;

@Repository
public interface IClienteRepo extends JpaRepository<Cliente,Long>{

    Optional<Cliente> findByUsuario(String username);
    
    
}
