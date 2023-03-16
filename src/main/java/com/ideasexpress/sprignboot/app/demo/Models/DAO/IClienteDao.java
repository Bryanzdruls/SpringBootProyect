package com.ideasexpress.sprignboot.app.demo.Models.DAO;

import java.util.List;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente; //ESto fue lo que acabmos de crear 

public interface IClienteDao {
    
    public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);
}
