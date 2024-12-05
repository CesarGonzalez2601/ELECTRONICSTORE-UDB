package com.app.web.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "tables")
public class Tables {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer idTable;

    @Column( name = "number")
    private Integer number;

    @Column( name = "description")
    private String description;

    @Column( name = "seats")
    private Integer seats;

    @Column( name = "isavailable")
    private Boolean isAbailable;

}
