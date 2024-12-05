package com.app.web.servicio;

import com.app.web.entidad.ProductCategories;
import com.app.web.repositorio.ProductCategoriesRepositorio;
import com.app.web.servicio.interfaces.ProductCategoriesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsCategoriesServicioImpl implements ProductCategoriesServicio {

    @Autowired
    private ProductCategoriesRepositorio repositorio;

    @Override
    public List<ProductCategories> listarCategorias() {
        return repositorio.findAll();
    }
}