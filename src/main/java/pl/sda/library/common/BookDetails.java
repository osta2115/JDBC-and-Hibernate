package pl.sda.library.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Getter
@Setter
@Entity
@Table(name = "books")
public class BookDetails {

    @Id
    private Integer id;

    @Column(length = 128)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    @Column(length = 32)
    private String publisher;

    @Column(name = "release_date")
    private Date releaseDate;
}
