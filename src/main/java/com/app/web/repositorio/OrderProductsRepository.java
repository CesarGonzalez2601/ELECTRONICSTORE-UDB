package com.app.web.repositorio;


import com.app.web.entidad.OrderProducts;
import com.app.web.entidad.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProducts, Integer> {
    List<OrderProducts> findByOrderIdIdOrder(Integer orderId);
}