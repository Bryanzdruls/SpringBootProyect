package com.ideasexpress.sprignboot.app.demo.Models.DAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Detalle;
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
        return em.createQuery
        ("from Ventas where id_cliente like:id ")
        .setParameter("id", id)
        .getResultList();
    }

    @Override
    public int totalFactura(List<Ventas> ventas) {
        return ventas.stream().flatMap(venta -> venta.getDetalle().stream())
                        .mapToInt(Detalle::getValorVenta)
                        .filter(valorventa ->  valorventa >=0)
                        .sum();
    }

    public List<Integer> subTotalList(List<Ventas> ventas,List<Detalle> detalles){
        int menor=  ventas.stream()
        .mapToInt(venta ->(int)(long)venta.getId())
        .min()
        .orElse(0);   
        System.out.println(menor);
        //System.out.println(ventas);

        int mayor=  ventas.stream()
        .mapToInt(venta ->(int)(long)venta.getId())
        .max()
        .orElse(0);
        System.out.println(mayor);
        List<Detalle> detalle=new ArrayList<>();
        int sum =0;
        if(menor>ventas.size())
        {
            for(int i=0;i<ventas.size();i++){
                final int j= menor;
                detalle =detalles.stream().filter(d ->d.getVenta().getId() == j)//1
                .collect(Collectors.toList());
    
                sum=detalle.stream().mapToInt(d->d.getValorVenta())
                                    .sum();

                System.out.println(sum);
                //System.out.println(ventas.get(i-1).toString());
                ventas.get(i).setSubtotal(sum);

                System.out.println(ventas.size());
                System.out.println(menor);
                menor++;
            }
        }else{
            for(int i=menor;i<=ventas.size();i++){
                final int j= i;
                detalle =detalles.stream().filter(d ->d.getVenta().getId() == j)//1
                .collect(Collectors.toList());
    
                sum=detalle.stream().mapToInt(d->d.getValorVenta())
                                    .sum();
                System.out.println(sum);
                //System.out.println(ventas.get(i-1).toString());
                ventas.get(i-1).setSubtotal(sum);
            }
        }

        return null;
    }
}

