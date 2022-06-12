package pl.sda.library.hibernate;


import lombok.extern.slf4j.Slf4j;
import pl.sda.library.common.Author;
import pl.sda.library.common.BookBasicInfo;
import pl.sda.library.common.BookDetails;
import pl.sda.library.common.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
public class LibraryMain {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static BooksJpaRepository booksJpaRepository;

    public static void main(String[] args) throws SQLException {
        entityManagerFactory = Persistence.createEntityManagerFactory("mysql-library");
        entityManager = entityManagerFactory.createEntityManager();
        booksJpaRepository = new BooksJpaRepository(entityManager);



        entityManager.close();
        entityManagerFactory.close();
    }

    private static void testCreateBook() throws SQLException {
        var simpleCategory = new Category();
        simpleCategory.setId(100);
        simpleCategory.setName("Simple category");

        var simpleAuthor = new Author();
        simpleAuthor.setId(100);
        simpleAuthor.setFirstName("Simple Firstname");
        simpleAuthor.setLastName("Simple Lastname");


        var bookDetails = new BookDetails();
            bookDetails.setId(100);
            bookDetails.setTitle("Simple title");
            bookDetails.setPublisher("Simple publisher");
            bookDetails.setCategory(simpleCategory);
            bookDetails.setAuthor(simpleAuthor);
            bookDetails.setReleaseDate(Date.valueOf(LocalDate.of(2020, 7,31)));

        booksJpaRepository.createBook(bookDetails);
    }

    private static void testGetBooksCount() throws SQLException {
        long booksCount = booksJpaRepository.getBooksCount();
        log.info("Books count: {}", booksCount);
    }

    private static void testDeleteBookById() throws SQLException {
        booksJpaRepository.deleteBookById(100);
    }

    private static void testBookBasicInfoById() throws SQLException {
        var bookBasicInfoOpt = booksJpaRepository.getBookBasicInfoById(50);
        bookBasicInfoOpt.ifPresent(bookBasicInfo -> log.info("Found: {}", bookBasicInfo));
    }

    private static void testGetAllBooks() throws SQLException {
        List<BookBasicInfo> allBooks = booksJpaRepository.getAllBooks();
        for (BookBasicInfo bookBasicInfo : allBooks) {
            log.info("{}", bookBasicInfo);
        }
    }
}
