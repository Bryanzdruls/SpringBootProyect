package com.ideasexpress.sprignboot.app.demo.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ideasexpress.sprignboot.app.demo.Models.DAO.IClienteDao;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;

@Controller
@SessionAttributes("cliente") //igual que todos los atributos
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @GetMapping("/listar")
    public String listar(Model model) {
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
    public String guardar(Cliente cliente, SessionStatus status) {
        clienteDao.save((cliente));
        status.setComplete();
        return "redirect:listar ";
    }

    @GetMapping("/form/{id}")
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
    public String eliminar(@PathVariable(value= "id") Long id, Model model)
    {
        if(id>0)clienteDao.delete(id);
        return "redirect:/listar";
    }
}
