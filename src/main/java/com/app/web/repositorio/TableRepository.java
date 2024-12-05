package com.app.web.repositorio;

import com.app.web.entidad.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Tables, Integer> {

    @Query("SELECT t FROM Tables t WHERE t.isAbailable = true AND t NOT IN (" +
            "SELECT r.tableId FROM TableReservations r WHERE r.status = 'Confirmada' " +
            "AND (r.reservationStartDate < :endDate AND r.reservationEndDate > :startDate))")
    List<Tables> findAvailableTables(@Param("startDate") LocalDateTime startDate,
                                     @Param("endDate") LocalDateTime endDate);
}
