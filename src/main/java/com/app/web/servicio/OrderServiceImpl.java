package com.app.web.servicio;

import com.app.web.entidad.OrderProducts;
import com.app.web.entidad.Orders;
import com.app.web.repositorio.OrderProductsRepository;
import com.app.web.repositorio.OrderRepository;
import com.app.web.servicio.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @Override
    public List<Orders> findAllOrders() {
        return orderRepository.findAllByStatusNative("activa");
    }

    @Override
    public Orders findOrderById(Integer id) {
        Optional<Orders> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Orders saveOrder(Orders order) {
        order.setCreationDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public void addProductToOrder(OrderProducts orderProduct) {
        orderProductsRepository.save(orderProduct);
    }

    @Override
    public void updateOrderStatus(Integer orderId, String status) {
        Orders order = findOrderById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public List<OrderProducts> findOrderProductsByOrderId(Integer orderId) {
        return orderProductsRepository.findByOrderIdIdOrder(orderId);
    }

}