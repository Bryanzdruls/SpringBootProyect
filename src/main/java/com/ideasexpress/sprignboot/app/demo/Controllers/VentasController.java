package com.ideasexpress.sprignboot.app.demo.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import com.ideasexpress.sprignboot.app.demo.Models.DAO.IVentasDao;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Ventas;

@Repository
@SessionAttributes("ventas")
@RequestMapping("/ventas")
public class VentasController {
    @Autowired
    private IVentasDao ventasDao;


    @GetMapping("/listar/{id}")
    public String listar(@PathVariable(value="id")Long id,Model model){

        model.addAttribute("tituloP", "Ventas");
        model.addAttribute("ventas", ventasDao.findOneCliente(id));
        
        return"/ventas/listar";
    }
    @GetMapping("/form") //Se ejecuta como caso de uso desde la web
    public String crear(Model model){
        Ventas venta = new Ventas();
        model.addAttribute("titulo", "Formulario de Productos");
        model.addAttribute("valor", "Crear producto");
        model.addAttribute("producto", venta);
        return "ventas/form";
    }
    @PostMapping(value="form") //Se ejecuta despues del Get Mapping
    public String guardar(@Valid Ventas venta, BindingResult bindingResult, SessionStatus status, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario de productos");
                model.addAttribute("valor", "Crear producto");
                model.addAttribute("producto", venta);
                return "/ventas/form";

        }
        ventasDao.save(venta);
        status.setComplete();
        //return "redirect:producto/listar";            //no funciona estando dentro de una carpeta
        return "redirect:/ventas/listar";    //metodo para redirigir cuando se esta dentro de una carpeta

    }
    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id,Model model){
        Ventas venta = null;
        if(id>0){
            venta=ventasDao.findOne(id);
        }else{
            return"redirect:/ventas/listar";
        }
        model.addAttribute("titulo", "Formulario de productos");
        model.addAttribute("valor", "Editar producto");
        model.addAttribute("producto", venta);
        return "ventas/form";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, Model model){
        if(id>0)ventasDao.delete(id);
        return "redirect:/ventas/listar";
    }


    
}
