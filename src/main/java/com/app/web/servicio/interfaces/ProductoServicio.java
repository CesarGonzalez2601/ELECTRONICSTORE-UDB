package com.app.web.servicio.interfaces;

import com.app.web.entidad.Products;

import java.util.List;

public interface ProductoServicio {

    public List<Products> listarTodosLosProductos();

    public Products guardarProducto(Products products);

    public Products obtenerProductoPorId(Integer id);

    public Products actualizaProducto(Products products);

    public void eliminarProducto(Integer id);
}
