package com.ideasexpress.sprignboot.app.demo.Models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Ventas;

@Repository
public class VentasDaoImp  implements IVentasDao{
    @PersistenceContext
    private EntityManager em;


    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)

    @Override
    public List<Ventas> findAll() {
        return em.createQuery("from Ventas ").getResultList();
    }

    @Transactional
    @Override
    public void save(Ventas venta) {
        if (venta.getId() != null && venta.getId() > 0) {
            em.merge(venta);
        } else {
            em.persist(venta);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Ventas findOne(Long id) {
        return em.find(Ventas.class, id);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        Ventas venta = findOne(id);
        em.remove(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> findOneCliente(Long id) {       
        return em.createQuery("from Ventas where id_cliente like:id ")
        .setParameter("id", id)
        .getResultList();
    }
    
}
