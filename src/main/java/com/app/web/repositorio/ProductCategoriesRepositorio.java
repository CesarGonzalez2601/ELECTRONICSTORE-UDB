package com.app.web.repositorio;

import com.app.web.entidad.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoriesRepositorio extends JpaRepository<ProductCategories, Integer> {
}