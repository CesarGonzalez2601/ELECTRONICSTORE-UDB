package com.app.web.repositorio;

import com.app.web.entidad.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TablesRepository extends JpaRepository<Tables, Integer> {

    @Query("SELECT r.tableId FROM TableReservations r WHERE r.status = 'Confirmada' " +
            "AND (r.reservationStartDate < :endDate AND r.reservationEndDate > :startDate)")
    List<Tables> findReservedTables(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);
}