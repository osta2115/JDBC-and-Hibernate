package pl.sda.introduction.hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString
@Setter
@Entity
@Table(name = "product_categories")
@Getter
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER)
    private Set<Product> products;
}
