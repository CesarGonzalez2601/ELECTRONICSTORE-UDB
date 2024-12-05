package com.app.web.controlador;

import com.app.web.entidad.Users;
import com.app.web.entidad.Employees;
import com.app.web.servicio.interfaces.UserService;
import com.app.web.servicio.interfaces.EmployeeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeServicio employeeService;

    // Listar todos los usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/users";
    }

    // Mostrar formulario para agregar un nuevo usuario
    @GetMapping("/add_user")
    public String mostrarFormularioAgregarUsuario(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("employees", employeeService.listarTodosLosEmpleados());
        return "users/add_user";
    }

    // Guardar un nuevo usuario
    @PostMapping
    public String guardarUsuario(@ModelAttribute Users user) {
        userService.save(user);
        return "redirect:/users";
    }

    // Mostrar formulario para editar un usuario
    @GetMapping("/edit/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable("id") Integer id, Model model) {
        Users user = userService.findById(id);
        List<Employees> employees = employeeService.listarTodosLosEmpleados();
        model.addAttribute("user", user);
        model.addAttribute("employees", employees);
        return "users/update_user";
    }

    // Guardar cambios de un usuario editado
    @PostMapping("/{id}")
    public String actualizarUsuario(@PathVariable("id") Integer id, @ModelAttribute Users user) {
        user.setUserId(id);
        userService.save(user);
        return "redirect:/users";
    }

    // Eliminar un usuario
    @GetMapping("/delete/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Optional<Users> user = Optional.ofNullable(userService.findById(id));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
