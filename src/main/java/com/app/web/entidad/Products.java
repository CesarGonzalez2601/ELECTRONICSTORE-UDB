package com.app.web.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer idProduct;

    @Column( name = "name")
    private String nameProduct;

    @Column( name = "description")
    private String description;

    @Column ( name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private ProductCategories caterodyId;

    @Column(name = "isavailable")
    private Boolean isAvailable;

}
