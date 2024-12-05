package com.app.web.repositorio;

import com.app.web.entidad.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepositorio extends JpaRepository<Products, Integer> {
}
