package com.app.web.servicio.interfaces;

import com.app.web.entidad.Orders;
import com.app.web.entidad.OrderProducts;

import java.util.List;

public interface OrderService {
    List<Orders> findAllOrders();
    Orders findOrderById(Integer id);
    Orders saveOrder(Orders order);
    void addProductToOrder(OrderProducts orderProduct);
    void updateOrderStatus(Integer orderId, String status);
    List<OrderProducts> findOrderProductsByOrderId(Integer orderId);
}