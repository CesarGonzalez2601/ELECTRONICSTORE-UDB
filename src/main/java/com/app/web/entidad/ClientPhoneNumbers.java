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
@Table( name = "clientphonenumbers")
public class ClientPhoneNumbers {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    public Integer clientPhoneNumbersId;

    @ManyToOne
    @JoinColumn( name = "clientid")
    public Clients clientId;

    @Column( name = "countrycode")
    public String countryCode;

    @Column( name = "phonenumber")
    public String phoneNumber;

}
