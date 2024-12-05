package com.app.web.servicio.interfaces;

import com.app.web.entidad.TableReservations;
import com.app.web.entidad.Tables;

import java.time.LocalDateTime;
import java.util.List;

public interface TableReservationService {

    List<Tables> obtenerMesasDisponibles(LocalDateTime startDate, LocalDateTime endDate);

    void crearReservacion(TableReservations reservation);

    // Método para listar todas las reservaciones
    List<TableReservations> listarTodasReservaciones();

    // Método para obtener una reservación por su ID
    TableReservations obtenerReservacionPorId(Integer id);

    // Método para actualizar una reservación existente
    void actualizarReservacion(TableReservations reservation);

    // Método para eliminar una reservación
    void eliminarReservacion(Integer id);

}
