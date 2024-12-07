package com.app.web.servicio;

import com.app.web.entidad.OrderProducts;
import com.app.web.entidad.Orders;
import com.app.web.repositorio.OrderProductsRepository;
import com.app.web.repositorio.OrderRepository;
import com.app.web.servicio.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    // Cola de órdenes
    private List<Orders> orderQueue = new ArrayList<>();

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
        // Agregar la orden a la cola
        orderQueue.add(order);
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

    // Métodos para manejar la cola

    /**
     * Ordenar la cola por fecha de creación (algoritmo Bubble Sort).
     */
    public void sortOrderQueueByCreationDate() {
        for (int i = 0; i < orderQueue.size() - 1; i++) {
            for (int j = 0; j < orderQueue.size() - i - 1; j++) {
                if (orderQueue.get(j).getCreationDate().isAfter(orderQueue.get(j + 1).getCreationDate())) {
                    Orders temp = orderQueue.get(j);
                    orderQueue.set(j, orderQueue.get(j + 1));
                    orderQueue.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Procesar la orden más antigua de la cola.
     */
    public Orders processNextOrder() {
        if (orderQueue.isEmpty()) {
            throw new RuntimeException("No hay órdenes en la cola para procesar.");
        }
        // Obtener la primera orden (la más antigua)
        Orders nextOrder = orderQueue.remove(0);
        nextOrder.setStatus("en_proceso");
        return orderRepository.save(nextOrder);
    }

    /**
     * Obtener todas las órdenes en la cola.
     */
    public List<Orders> getOrderQueue() {
        return new ArrayList<>(orderQueue);
    }

    /**
     * Ordenar la cola por un campo específico usando Comparator (orden flexible).
     * @param comparator Comparator para definir el orden
     */
    public void sortOrderQueue(Comparator<Orders> comparator) {
        orderQueue.sort(comparator);
    }

    @Override
    public void addOrderToQueue(Orders order) {
        orderQueue.add(order); // Añade la orden a la cola
    }
}
