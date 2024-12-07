package com.app.web.servicio;

import com.app.web.entidad.Clients;
import com.app.web.entidad.Products;
import com.app.web.repositorio.OrderRepository;
import com.app.web.repositorio.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private final OrderRepository orderRepository;
    private final ProductsRepository productRepository;

    public RecommendationService(OrderRepository orderRepository, ProductsRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Products> recommendProducts(Integer clientId) {
        Graph graph = new Graph();

        // Construir grafo
        List<Products> clientProducts = orderRepository.findProductsByClient(clientId);
        for (Products product : clientProducts) {
            List<Clients> clients = orderRepository.findClientsByProduct(product.getIdProduct());
            for (Clients client : clients) {
                graph.addEdge(clientId, product.getIdProduct(), 1); // Peso inicial
            }
        }

        // Recorrer grafo para obtener recomendaciones
        Map<Integer, Integer> productScores = graph.getEdges(clientId);
        return productScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> productRepository.findById(entry.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

