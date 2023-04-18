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

import com.ideasexpress.sprignboot.app.demo.Models.DAO.IClienteDao;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;
import com.ideasexpress.sprignboot.app.demo.Service.Insercion;

@Controller
@SessionAttributes("cliente") //igual que todos los atributos
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;


    @Autowired
    private Insercion insercion;

    private int sw=0;
    @GetMapping("/listar")
    public String listar(Model model) {
        if(sw==0){
            insercion.ventasPorCliente();
            sw++;
        };
        //System.out.println(clienteDao.findOne(new Long(1)).toString());

        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Cliente cliente = new Cliente();

        model.addAttribute("titulo", "Formulario de clientes");
        model.addAttribute("valor", "Crear cliente");
        model.addAttribute("cliente", cliente);
        return "form";
    }

    // @RequestMapping(value = "/form", method = ResquestMethod.POST)

    @PostMapping(value = "/form")
    //@PostMapping(value = "/form")
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, SessionStatus status, Model model) {      
        if (bindingResult.hasErrors()){
                model.addAttribute("titulo", "Formulario de clientes");
                model.addAttribute("valor", "Crear cliente");
                model.addAttribute("cliente", cliente);
                return "form";
            }           
            clienteDao.save(cliente);
            status.setComplete();
            return "redirect:/cliente/listar";
    }
    /*public ModelAndView guardar(@Valid Cliente cliente, BindingResult bindingResult, SessionStatus status, Model model) {      
        if (bindingResult.hasErrors()){
                model.addAttribute("valor", "Crear cliente");
                return new ModelAndView("form").addObject("cliente", cliente);
            }

            clienteDao.save((cliente));
            return new ModelAndView("redirect:/cliente/listar");
    }*/

    @GetMapping("/form/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editar(@PathVariable(value = "id") Long id, Model model) {

        Cliente cliente = null;

        if (id > 0) {
            cliente = clienteDao.findOne(id);
        }
        else{
            return "redirect:/listar";
        }
        
        model.addAttribute("titulo", "Formulario de clientes");
        model.addAttribute("valor", "Editar cliente");
        model.addAttribute("cliente", cliente);
        return "form";
    }
    @GetMapping("/eliminar/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String eliminar(@PathVariable(value= "id") Long id, Model model)
    {
        if(id>0)clienteDao.delete(id);
        return "redirect:/cliente/listar";
    }

    @GetMapping("/ventas/{id}")
    public String ventasCliente(@PathVariable(value = "id") Long id,Model model){
        Cliente cliente = null;
        if(id>0){
            cliente = clienteDao.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.addAttribute("cliente", cliente);
        return "/ventas/listar";
    }

}
