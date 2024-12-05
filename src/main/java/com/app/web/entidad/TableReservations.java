package com.app.web.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "tablereservations")
public class TableReservations {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer idTableResevations;

    @ManyToOne
    @JoinColumn ( name = "clientid")
    private Clients clientId;

    @ManyToOne
    @JoinColumn ( name = "tableid")
    private Tables tableId;

    @Column (name = "reservationstartdate")
    private LocalDateTime reservationStartDate;

    @Column ( name = "reservationenddate")
    private LocalDateTime reservationEndDate;

    @Column ( name = "status")
    private String status;

}
