package pl.sda.introduction.hibernate;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}