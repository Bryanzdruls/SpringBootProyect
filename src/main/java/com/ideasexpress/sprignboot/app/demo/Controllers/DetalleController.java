package com.ideasexpress.sprignboot.app.demo.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ideasexpress.sprignboot.app.demo.Models.DAO.IDetalleDao;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Detalle;

@Controller
@SessionAttributes("detalle")
@RequestMapping("/detalle")
public class DetalleController {
    @Autowired
    private IDetalleDao detalleDao;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("tituloP", "Listado de Productos");
        model.addAttribute("productos", detalleDao.findAll());
        return"/producto/listar";
    }
    
    @GetMapping("/form") //Se ejecuta como caso de uso desde la web
    public String crear(Model model){
        Detalle detalle = new Detalle();
        model.addAttribute("titulo", "Formulario de Productos");
        model.addAttribute("valor", "Crear producto");
        model.addAttribute("producto", detalle);
        return "producto/form";
    }
    @PostMapping(value="form") //Se ejecuta despues del Get Mapping
    public String guardar(@Valid Detalle detalle, BindingResult bindingResult, SessionStatus status, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario de productos");
                model.addAttribute("valor", "Crear producto");
                model.addAttribute("producto", detalle);
                return "/detalle/form";

        }
        detalleDao.save(detalle);
        status.setComplete();
        //return "redirect:producto/listar";            //no funciona estando dentro de una carpeta
        return "redirect:/detalle/listar";    //metodo para redirigir cuando se esta dentro de una carpeta

    }
    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id,Model model){
        Detalle detalle = null;
        if(id>0){
            detalle=detalleDao.findOne(id);
        }else{
            return"redirect:/detalle/listar";
        }
        model.addAttribute("titulo", "Formulario de productos");
        model.addAttribute("valor", "Editar producto");
        model.addAttribute("producto", detalle);
        return "producto/form";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, Model model){
        if(id>0)detalleDao.delete(id);
        return "redirect:/detalle/listar";
    }
}
