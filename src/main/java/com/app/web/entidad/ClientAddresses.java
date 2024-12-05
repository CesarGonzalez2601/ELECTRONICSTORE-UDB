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
@Table( name = "clientaddresses")
public class ClientAddresses {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer clientAdressesId;

    @Column( name = "country")
    private String country;

    @Column ( name = "state")
    private String state;

    @Column ( name = "city")
    private String city;

    @Column( name = "address")
    private String address;

    @Column( name = "zipcode")
    private String zipcode;

}
