package com.app.web.controlador;

import com.app.web.entidad.OrderProducts;
import com.app.web.entidad.Orders;
import com.app.web.entidad.Products;
import com.app.web.entidad.Users;
import com.app.web.repositorio.UsersRepository;
import com.app.web.servicio.interfaces.ClienteServicio;
import com.app.web.servicio.interfaces.OrderService;
import com.app.web.servicio.interfaces.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductoServicio productService;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public String listarOrdenes(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "orders/orders";
    }

    @GetMapping("/create")
    public String mostrarFormularioOrden(Model model) {
        model.addAttribute("order", new Orders());
        model.addAttribute("clients", clienteServicio.listarTodosLosClientes());
        return "orders/create_order";
    }

    @PostMapping
    public String guardarOrden(@ModelAttribute Orders order) {
        order.setStatus("activa");
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/details/{id}")
    public String verDetalleOrden(@PathVariable Integer id, Model model) {
        Orders order = orderService.findOrderById(id);
        List<OrderProducts> orderProducts = orderService.findOrderProductsByOrderId(id);

        double totalPrice = orderProducts.stream()
                .mapToDouble(op -> op.getQuantity() * op.getProductId().getPrice().doubleValue())
                .sum();

        model.addAttribute("order", order);
        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("products", productService.listarTodosLosProductos()); // Para agregar nuevos productos
        model.addAttribute("totalPrice", totalPrice);
        return "orders/order_details";
    }

    @PostMapping("/{id}/add-product")
    public String agregarProductoOrden(@PathVariable Integer id, @RequestParam Integer productId, @RequestParam Integer quantity, @RequestParam String comments) {
        Orders order = orderService.findOrderById(id);
        Products product = productService.obtenerProductoPorId(productId);

        OrderProducts orderProduct = new OrderProducts();
        orderProduct.setOrderId(order);
        orderProduct.setProductId(product);
        orderProduct.setQuantity(quantity);
        orderProduct.setComments(comments);

        orderService.addProductToOrder(orderProduct);
        return "redirect:/orders/details/" + id;
    }

    @PostMapping("/{id}/update-status")
    public String actualizarEstadoOrden(@PathVariable Integer id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/orders";
    }

    @PostMapping("/{id}/dispatch")
    public String despacharOrden(@PathVariable Integer id, @RequestParam Integer dispatchedBy) {
        Orders order = orderService.findOrderById(id);
        System.out.println("Dispatched By: " + dispatchedBy);

        Users Usuario = usersRepository.getById(dispatchedBy);

        order.setDispatchedby(Usuario);
        order.setPreparedby(Usuario);
        order.setStatus("Despachada"); // Cambiar el estado de la orden a "Despachada"

        orderService.saveOrder(order); // Actualizar la orden en la base de datos

        return "redirect:/orders"; // Redirigir a la lista de órdenes
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {

        Orders order = orderService.findOrderById(id);

        if (order != null) {
            orderService.updateOrderStatus(id, "eliminada");
            return "redirect:/orders?success=OrderDeleted";
        } else {
            // Redirigir a la lista de órdenes con un mensaje de error
            return "redirect:/orders?error=OrderNotFound";
        }
    }

    // *** Nuevas funcionalidades relacionadas con la cola de órdenes ***

    // Mostrar la cola de órdenes
    @GetMapping("/queue")
    public String mostrarColaOrdenes(Model model) {
        List<Orders> orderQueue = orderService.getOrderQueue();
        model.addAttribute("orderQueue", orderQueue);
        return "orders/order_queue";
    }

    // Ordenar la cola de órdenes por fecha de creación
    @GetMapping("/queue/sort")
    public String ordenarColaOrdenes(Model model) {
        orderService.sortOrderQueueByCreationDate();
        List<Orders> sortedQueue = orderService.getOrderQueue();
        model.addAttribute("orderQueue", sortedQueue);
        return "orders/order_queue";
    }

    // Procesar la próxima orden en la cola
    @PostMapping("/queue/process")
    public String procesarSiguienteOrden(Model model) {
        try {
            Orders processedOrder = orderService.processNextOrder();
            model.addAttribute("message", "Orden procesada: " + processedOrder.getIdOrder());
        } catch (RuntimeException e) {
            model.addAttribute("error", "No hay órdenes en la cola para procesar.");
        }
        return "redirect:/orders/queue";
    }

    // Agregar una orden a la cola
    @PostMapping("/{id}/queue/add")
    public String agregarOrdenACola(@PathVariable Integer id, Model model) {
        Orders order = orderService.findOrderById(id);
        if (order != null) {
            orderService.addOrderToQueue(order); // Llama al servicio para añadir la orden a la cola
        }
        // Obtén la cola actualizada para mostrar en la vista
        List<Orders> orderQueue = orderService.getOrderQueue();
        model.addAttribute("orderQueue", orderQueue);
        return "orders/order_queue";
    }


}

