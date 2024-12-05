package com.app.web.servicio;

import com.app.web.entidad.TableReservations;
import com.app.web.entidad.Tables;
import com.app.web.repositorio.TableRepository;
import com.app.web.repositorio.TablesReservationRepository;
import com.app.web.servicio.interfaces.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TableReservationServiceImpl implements TableReservationService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TablesReservationRepository tableReservationRepository;

    @Override
    public List<Tables> obtenerMesasDisponibles(LocalDateTime startDate, LocalDateTime endDate) {
        return tableRepository.findAvailableTables(startDate, endDate);
    }

    @Override
    public void crearReservacion(TableReservations reservation) {
        tableReservationRepository.save(reservation);
    }

    @Override
    public List<TableReservations> listarTodasReservaciones() {
        return tableReservationRepository.findAll();
    }

    @Override
    public TableReservations obtenerReservacionPorId(Integer id) {
        return tableReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservación no encontrada con ID: " + id));
    }

    @Override
    public void actualizarReservacion(TableReservations reservation) {
        tableReservationRepository.save(reservation);
    }

    @Override
    public void eliminarReservacion(Integer id) {
        tableReservationRepository.deleteById(id);
    }
}