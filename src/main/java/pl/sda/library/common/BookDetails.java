package pl.sda.library.common;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "books")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDetails {

    @Id
    private Integer id;

    @Column(length = 128)
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author")
    private Author author;

    @Column(length = 32)
    private String publisher;

    @Column(name = "release_date")
    private Date releaseDate;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "bookDetails")
    private Set<Rent> rents;
}
