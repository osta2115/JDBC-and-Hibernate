package pl.sda.library.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {

    @Id
    private Integer id;

    @Column(name = "first_name", length = 32)
    private String firstName;


    @Column(name = "last_name", length = 32)
    private String lastName;
}
