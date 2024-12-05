package com.app.web.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("usuarioEnSesion")
    public Object agregarUsuarioEnSesion(HttpSession session) {
        // Obtiene el usuario de la sesión
        return session.getAttribute("usuario");
    }
}
