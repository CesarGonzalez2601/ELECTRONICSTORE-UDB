package com.app.web.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer employeeId;

    @Column (name = "firstname")
    private String firstName;

    @Column (name = "lastname")
    private String lastName;

    @Column (name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Column (name = "dateofjoining")
    private LocalDate dateOfJoining;

    @Column (name = "dateofleaving")
    private LocalDate dateOfLeaving;

    @Column (name = "isactive")
    private Boolean isActive;

}
