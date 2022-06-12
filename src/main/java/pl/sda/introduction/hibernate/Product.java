package pl.sda.introduction.hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Entity
@Table(name = "products")
@Setter
@Getter
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

    @Column(name = "update_datetime")
    private LocalDateTime updateDatetime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id", nullable = false)
    private ProductDetails productDetails;

    void decreasePrice(BigDecimal decreasValue) {
        var currentPriceValue = price.getPriceValue();
        var newPriceValue = currentPriceValue.subtract(decreasValue);
        price.setPriceValue(newPriceValue);
    }

    @ManyToMany(mappedBy = "products")
    private List<Customer> customers;

}
