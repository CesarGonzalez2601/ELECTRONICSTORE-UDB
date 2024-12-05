package com.app.web.servicio;

import com.app.web.entidad.Products;
import com.app.web.repositorio.ProductsRepositorio;
import com.app.web.servicio.interfaces.ProductsServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServicioImpl implements ProductsServicio {

    @Autowired
    private ProductsRepositorio repositorio;

    @Override
    public List<Products> listarProductos() {
        return repositorio.findAll();
    }

    @Override
    public Products guardarProducto(Products product) {
        return repositorio.save(product);
    }

    @Override
    public Products obtenerProductoPorId(Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Products actualizarProducto(Products product) {
        return repositorio.save(product);
    }

    @Override
    public void eliminarProducto(Integer id) {
        repositorio.deleteById(id);
    }
}
