package com.ideasexpress.sprignboot.app.demo.Controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.IProductoDao;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Producto;

@Controller
@SessionAttributes("producto")//Nombre atributo
//@RequestMapping(value="/api")
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private IProductoDao productoDao;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de Productos");
        model.addAttribute("productos", productoDao.findAll());
        return"/producto/listar";
    }
    
    @GetMapping("/form") //Se ejecuta como caso de uso desde la web
    public String crear(Model model){
        Producto producto = new Producto();
        model.addAttribute("titulo", "Formulario de Productos");
        model.addAttribute("valor", "Crear producto");
        model.addAttribute("producto", producto);
        return "producto/form";
    }
    @PostMapping(value="/form") //Se ejecuta despues del Get Mapping
    public RedirectView guardar(@Valid Producto producto, SessionStatus status){
        productoDao.save(producto);
        status.setComplete();
        //return "redirect:producto/listar";            //no funciona estando dentro de una carpeta
        return new RedirectView("/producto/listar");    //metodo para redirigir cuando se esta dentro de una carpeta

    }
    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id,Model model){
        Producto producto = null;
        if(id>0){
            producto=productoDao.findOne(id);
        }else{
            return"redirect:/producto/listar";
        }
        model.addAttribute("titulo", "Formulario de productos");
        model.addAttribute("valor", "Editar producto");
        model.addAttribute("producto", producto);
        return "producto/form";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, Model model){
        if(id>0)productoDao.delete(id);
        return "redirect:/producto/listar";
    }

}
