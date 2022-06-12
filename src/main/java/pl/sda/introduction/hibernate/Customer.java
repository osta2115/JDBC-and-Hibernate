package pl.sda.introduction.hibernate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany
    @JoinTable(name="customers_products",
            joinColumns=
            @JoinColumn(name="customer_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="product_id", referencedColumnName="id")
    )
    private List<Product> products;

    @Column(name = "customer_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;
}
