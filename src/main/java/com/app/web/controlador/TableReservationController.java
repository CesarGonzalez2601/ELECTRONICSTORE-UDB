package com.app.web.controlador;

import com.app.web.entidad.Clients;
import com.app.web.entidad.TableReservations;
import com.app.web.entidad.Tables;
import com.app.web.repositorio.ClientsRepository;
import com.app.web.repositorio.TableRepository;
import com.app.web.servicio.interfaces.ClienteServicio;
import com.app.web.servicio.interfaces.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class TableReservationController {

    @Autowired
    private TableReservationService tableReservationService;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/all")
    public String listarReservaciones(Model model) {
        List<TableReservations> reservations = tableReservationService.listarTodasReservaciones(); // Método del servicio
        model.addAttribute("reservations", reservations);
        return "tables/all_reservations"; // Nombre de la vista Thymeleaf
    }

    @GetMapping("/available-tables-selected")
    public String select(Model model) {
        model.addAttribute("clients", clienteServicio.listarTodosLosClientes());
        return "tables/available_tables_select"; // La plantilla donde se carga el formulario
    }

    @GetMapping("/available-tables")
    public String mostrarMesasDisponibles(  @RequestParam("startDate") String startDate,
                                            @RequestParam("endDate") String endDate,
                                            @RequestParam("clientId") Integer clientId,
                                            Model model) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        List<Tables> availableTables = tableReservationService.obtenerMesasDisponibles(start, end);

        System.out.println("DEBUGGGGGGGGGGGGGGGGG" + availableTables);

        model.addAttribute("availableTables", availableTables);
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);
        model.addAttribute("clientId", clientId);
        System.out.println(clientId);

        return "tables/available_tables.html";
    }

    @PostMapping("/new")
    public String createReservation(
            @RequestParam Integer tableId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Integer clientId) {

        // Obtener las entidades de Table y Client desde sus repositorios
        Tables table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada con ID: " + tableId));
        Clients client = clientsRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + clientId));

        // Crear la reservación
        TableReservations reservation = new TableReservations();
        reservation.setTableId(table);
        reservation.setClientId(client);
        reservation.setReservationStartDate(LocalDateTime.parse(startDate));
        reservation.setReservationEndDate(LocalDateTime.parse(endDate));
        reservation.setStatus("Confirmada");

        // Guardar la reservación
        tableReservationService.crearReservacion(reservation);

        return "admin";
    }
}

