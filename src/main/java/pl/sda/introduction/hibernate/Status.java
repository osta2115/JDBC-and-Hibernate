package pl.sda.introduction.hibernate;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "customer_stasuses")
@Setter
public class Status {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 15, unique = true, nullable = false)
    private String name;
}
