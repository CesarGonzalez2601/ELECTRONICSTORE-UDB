package com.app.web.servicio.interfaces;

import com.app.web.entidad.OrderProducts;

import java.util.List;

public interface OrderProductsService {
    List<OrderProducts> findOrderProductsByOrderId(Integer orderId);
}
