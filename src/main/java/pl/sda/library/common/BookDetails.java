package pl.sda.library.common;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class BookDetails {

    private int id;
    private String title;
    private int categoryId;
    private int authorId;
    private String publisher;
    private Date releaseDate;
}
