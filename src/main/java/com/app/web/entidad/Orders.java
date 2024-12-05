package com.app.web.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer idOrder;

    @ManyToOne
    @JoinColumn(name = "clientid")
    private Clients clientId;

    @Column (name = "ordertype")
    private String orderType;

    @Column (name = "status")
    private String status;

    @ManyToOne
    @JoinColumn( name = "createdby")
    private Users createdBy;

    @ManyToOne
    @JoinColumn( name = "dispatchedby")
    private Users dispatchedby;

    @ManyToOne
    @JoinColumn( name = "preparedby")
    private Users preparedby;


    @Column( name = "creationdate")
    private LocalDateTime creationDate;

    @Column( name = "preparationdate")
    private LocalDateTime preparationDate;

    @Column( name = "dispatchdate")
    private LocalDateTime dispatchDate;
}
