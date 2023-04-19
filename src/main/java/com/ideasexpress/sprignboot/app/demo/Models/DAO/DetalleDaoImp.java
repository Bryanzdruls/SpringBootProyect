package com.ideasexpress.sprignboot.app.demo.Models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Detalle;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Producto;


@Repository
public class DetalleDaoImp implements IDetalleDao {

    @PersistenceContext
    private EntityManager em;


    @SuppressWarnings("unchecked")
    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    @Override
    public List<Detalle> findAll() {
        return em.createQuery("from producto").getResultList();
    }

    @Transactional
    @Override
    public void save(Detalle detalle) {
        if (detalle.getId() != null && detalle.getId() > 0) {
            em.merge(detalle);
        } else {
            em.persist(detalle);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Detalle findOne(Long id) {
        return em.find(Detalle.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Detalle detalle = findOne(id);
        em.remove(detalle);
    }

    @Override
    public List<Detalle> findVentas(Long id) {
        return em.createQuery("from Detalle as d inner join fetch d.ventaMap where id_cliente like:id"

        , Detalle.class)
        .setParameter("id", id)
        .getResultList();
        
    }

    @Override
    public void unirProd(Producto producto) {
        System.out.println("el producto "+ producto.toString());
        Detalle detalle =  new Detalle(0, 0, producto);
    }
}
