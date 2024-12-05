package com.app.web.servicio.interfaces;
import com.app.web.entidad.Products;
import java.util.List;

public interface ProductsServicio {
    List<Products> listarProductos();
    Products guardarProducto(Products product);
    Products obtenerProductoPorId(Integer id);
    Products actualizarProducto(Products product);
    void eliminarProducto(Integer id);
}
