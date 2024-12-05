package com.app.web.controlador;

import com.app.web.entidad.Users;
import com.app.web.servicio.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedirectController {

    @Autowired
    private UserService userService;

    @GetMapping("/redirect")
    public String redirectToMenu(@RequestParam("userId") Integer userId) {
        Users user = userService.findById(userId);

        if (user != null) {
            if ("ADMIN".equals(user.getRole())) {
                return "admin";
            } else if ("USER".equals(user.getRole())) {
                return "user";
            }
        }

        return "redirect:/login"; // Si no existe o no tiene rol válido
    }
}