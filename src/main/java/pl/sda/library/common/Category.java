package pl.sda.library.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(length = 32)
    private String name;

    @Column(length = 64)
    private String description;
}