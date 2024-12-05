package com.app.web.servicio;

import com.app.web.entidad.OrderProducts;
import com.app.web.entidad.Orders;
import com.app.web.repositorio.OrderProductsRepository;
import com.app.web.repositorio.OrderRepository;
import com.app.web.servicio.interfaces.OrderProductsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderProductsImpl implements OrderProductsService {

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderProducts> findOrderProductsByOrderId(Integer orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        return orderProductsRepository.findByOrderIdIdOrder(order.getIdOrder());
    }

}
