package com.app.web.repositorio;

import com.app.web.entidad.Clients;
import com.app.web.entidad.Orders;
import com.app.web.entidad.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT * FROM orders WHERE status = :status", nativeQuery = true)
    List<Orders> findAllByStatusNative(@Param("status") String status);

    @Query("SELECT op.productId FROM OrderProducts op WHERE op.orderId.clientId.idClient = :clientId")
    List<Products> findProductsByClient(@Param("clientId") Integer clientId);

    @Query("SELECT o.clientId FROM OrderProducts op JOIN op.orderId o WHERE op.productId.idProduct = :productId")
        List<Clients> findClientsByProduct(@Param("productId") Integer productId);
}