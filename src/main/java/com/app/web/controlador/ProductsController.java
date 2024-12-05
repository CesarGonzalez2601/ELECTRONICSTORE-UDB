package com.app.web.controlador;

import com.app.web.entidad.ProductCategories;
import com.app.web.entidad.Products;
import com.app.web.servicio.interfaces.ProductsServicio;
import com.app.web.servicio.interfaces.ProductCategoriesServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductsController {

    @Autowired
    private ProductsServicio productsServicio;

    @Autowired
    private ProductCategoriesServicio productCategoriesServicio;

    @GetMapping({"/products", "/"})
    public String listarProductos(Model modelo) {
        modelo.addAttribute("products", productsServicio.listarProductos());
        return "products/products";
    }

    @GetMapping("/products/add_product")
    public String mostrarFormularioDeRegistrarProducto(Model modelo) {
        modelo.addAttribute("products", new Products());
        modelo.addAttribute("categories", productCategoriesServicio.listarCategorias());
        return "products/add_product";
    }

    @PostMapping("/products")
    public String guardarProducto(@ModelAttribute("product") Products product) {
        productsServicio.guardarProducto(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String mostrarFormularioDeEditarProducto(@PathVariable Integer id, Model modelo) {
        modelo.addAttribute("product", productsServicio.obtenerProductoPorId(id));
        modelo.addAttribute("categories", productCategoriesServicio.listarCategorias());
        return "products/update_product";
    }

    @PostMapping("/products/{idProduct}")
    public String actualizarProducto(@PathVariable Integer idProduct, @ModelAttribute("product") Products product,
                                     Model modelo) {
        Products productExistente = productsServicio.obtenerProductoPorId(idProduct);
        productExistente.setIdProduct(idProduct);
        productExistente.setNameProduct(product.getNameProduct());
        productExistente.setDescription(product.getDescription());
        productExistente.setPrice(product.getPrice());
        productExistente.setCaterodyId(product.getCaterodyId());
        productExistente.setIsAvailable(product.getIsAvailable());

        productsServicio.actualizarProducto(productExistente);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productsServicio.eliminarProducto(id);
        return "redirect:/products";
    }
}
