package com.app.web.controlador;

import com.app.web.entidad.Users;
import com.app.web.repositorio.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsersRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Muestra el formulario de login
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Model model,
                                HttpSession session) {

        Optional<Users> usuarioOpt = usuarioRepository.findByUsername(username);

        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            Users usuario = usuarioOpt.get();
            model.addAttribute("userId", usuario.getUserId());

            System.out.println("DEBUGGGGGGGGGGG" +usuario.getUserId());
            if ("admin".equalsIgnoreCase(usuario.getRole())) {
                return "admin"; // Redirige a la vista de administrador
            } else {
                return "usuarios"; // Redirige a la vista de usuario
            }
        } else {
            // Si la autenticación falla, mostrar un mensaje de error
            model.addAttribute("error", true);
            return "login";
        }
    }

    @PostMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        // Invalida la sesión del usuario
        session.invalidate();
        return "redirect:/login"; // Redirige al formulario de login
    }
}

