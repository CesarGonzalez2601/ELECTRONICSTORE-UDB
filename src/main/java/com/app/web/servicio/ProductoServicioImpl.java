package com.app.web.servicio;

import com.app.web.entidad.Products;
import com.app.web.repositorio.ProductsRepository;
import com.app.web.servicio.interfaces.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Products> listarTodosLosProductos() {
        return productsRepository.findAll();
    }

    @Override
    public Products guardarProducto(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public Products obtenerProductoPorId(Integer id) {
        return productsRepository.findById(id).get();
    }

    @Override
    public Products actualizaProducto(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public void eliminarProducto(Integer id) {
        productsRepository.deleteById(id);
    }

}
