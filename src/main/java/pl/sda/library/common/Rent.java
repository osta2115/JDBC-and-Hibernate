package pl.sda.library.common;

import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Entity
@Table(name = "rents")
public class Rent {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book")
    private BookDetails bookDetails;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;


}
