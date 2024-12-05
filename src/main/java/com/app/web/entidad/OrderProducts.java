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
@Table(name = "orderproducts")
public class OrderProducts {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer idOrderProducts;

    @ManyToOne
    @JoinColumn( name = "orderid")
    private Orders orderId;

    @ManyToOne
    @JoinColumn( name = "productid")
    private Products productId;

    @Column( name = "quantity")
    private Integer quantity;

    @Column (name = "comments")
    private String comments;

}
