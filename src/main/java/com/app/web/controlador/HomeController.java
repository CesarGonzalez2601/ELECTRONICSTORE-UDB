package com.app.web.controlador;

import com.app.web.entidad.Clients;
import com.app.web.entidad.Products;
import com.app.web.entidad.Users;
import com.app.web.repositorio.ClientsRepository;
import com.app.web.repositorio.ProductsRepository;
import com.app.web.repositorio.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/home")
    public String mostrarHome() {
        return "home"; // Muestra la página de inicio
    }

    @GetMapping("/pagina1")
    public String mostrarClientes(Model model) {
        List<Clients> clients = clientsRepository.findAll(); // Obtiene todos los clientes
        model.addAttribute("clientes", clients); // Añade la lista de clientes al modelo
        return "clientes";
    }

    @GetMapping("/pagina2")
    public String mostrarPagina2(Model model) {
        List<Products> products = productsRepository.findAll();
        model.addAttribute("productos", products);
        return "productos";
    }

    @GetMapping("/pagina3")
    public String mostrarPagina3(Model model) {
        List<Users> users = usersRepository.findAll();
        model.addAttribute("usuarios", users);
        return "usuarios";
    }
}
