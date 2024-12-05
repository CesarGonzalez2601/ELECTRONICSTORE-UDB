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
@Table ( name = "productcategories")
public class ProductCategories {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer idProductCategories;

    @Column(name = "name")
    private String name;

    @Column( name = "description")
    private String description;

    @Column( name = "isavailable")
    private Boolean isAvailable;
}
