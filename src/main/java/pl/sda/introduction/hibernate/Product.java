package pl.sda.introduction.hibernate;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@ToString
@Entity
@Table(name = "products")
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "product_description", nullable = false, length = 100)
    private String productDescription;

//    @Transient
//    private String myField;
    @Embedded
    private Price price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory productCategory;
}
