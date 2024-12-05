package com.app.web.repositorio;

import com.app.web.entidad.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT * FROM orders WHERE status = :status", nativeQuery = true)
    List<Orders> findAllByStatusNative(@Param("status") String status);

}