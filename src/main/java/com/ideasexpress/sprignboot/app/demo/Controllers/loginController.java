package com.ideasexpress.sprignboot.app.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @GetMapping("/login")

    public String login(Model model)
    {
        model.addAttribute("titulo", "INICIAR SESIÓN");

        return "login";
    }
    
    @GetMapping("/logout")

    public String logout()
    {
        return "login";
    }

}
