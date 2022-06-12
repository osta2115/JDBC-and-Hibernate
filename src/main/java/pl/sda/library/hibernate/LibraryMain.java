package pl.sda.library.hibernate;


import lombok.extern.slf4j.Slf4j;
import pl.sda.library.common.BookBasicInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
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

        testGetBooksCount();

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void testGetBooksCount() throws SQLException {
        long booksCount = booksJpaRepository.getBooksCount();
        log.info("Books count: {}", booksCount);
    }

    private static void testDeleteBookById() throws SQLException {
        booksJpaRepository.deleteBookById(50);
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
