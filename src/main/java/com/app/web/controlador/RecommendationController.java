package com.app.web.controlador;

import com.app.web.entidad.Products;
import com.app.web.repositorio.ClientsRepository;
import com.app.web.servicio.RecommendationService;
import com.app.web.servicio.interfaces.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecommendationController {
    private final ClientsRepository clientsRepository;
    private final RecommendationService recommendationService;

    @Autowired
    private final ClienteServicio clienteServicio;

    public RecommendationController(ClientsRepository clientsRepository, RecommendationService recommendationService, ClienteServicio clienteServicio) {
        this.clientsRepository = clientsRepository;
        this.recommendationService = recommendationService;
        this.clienteServicio = clienteServicio;
    }

    @GetMapping({"/recommendations", "/"})
    public String showRecommendationForm(Model model) {
        model.addAttribute("clients", clienteServicio.listarTodosLosClientes());
        return "recommendations/recommendation_form";
    }

    @GetMapping("/recommendations/products")
    public String showRecommendedProducts(@RequestParam("clientId") Integer clientId, Model model) {
        List<Products> recommendedProducts = recommendationService.recommendProducts(clientId);
        model.addAttribute("recommendedProducts", recommendedProducts);
        return "recommendations/recommended_products";
    }
}
