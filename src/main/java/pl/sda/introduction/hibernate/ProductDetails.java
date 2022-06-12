package pl.sda.introduction.hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
public class ProductDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer carbonaceous;

    @Column(nullable = false)
    private Integer protein;

    @Column(nullable = false)
    private Integer fat;

    @Column(nullable = false)
    private Integer kcal;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @OneToOne(mappedBy = "productDetails")
    @JoinColumn(name = "product_id")
    private Product product;
}
