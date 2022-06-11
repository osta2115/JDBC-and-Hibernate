package pl.sda.library;

import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class LibraryMain {


    public static void main(String[] args) {

//        try (var connection = ConnectionFactory.createH2Connection()) {
//            log.info("Successfully connected to H2 DB");
//        } catch (SQLException e) {
//            log.error("Something went wrong", e);
//        }

        try(var connection = ConnectionFactory.createMySqlConnection()) {
            var booksRepository = new BooksJDBCRepository(connection);
            //int requestedBookId = 3;

            //testGetBookById(booksRepository, requestedBookId);
            //testUpdateBookTitle(booksRepository, requestedBookId);
            //testGetBookById(booksRepository, requestedBookId);

            //testGetAllBooks(booksRepository);

//            testGetBookById(booksRepository, requestedBookId);
//            testDeleteBookById(booksRepository, requestedBookId);
//            testGetBookById(booksRepository, requestedBookId);

            //testCreateBook(booksRepository);
            //testDeleteBookById(booksRepository, 51);
            //testUpdateBook(booksRepository);
            testGetBooksCount(booksRepository);

        } catch (SQLException e) {
            log.error("Something went wrong", e);
        }
    }

    private static void testCreateBook(BooksJDBCRepository booksJDBCRepository) throws SQLException {
        var bookDetails = BookDetails.builder()
                .title("Newly created book")
                .authorId(1)
                .categoryId(3)
                .publisher("Helion")
                .releaseDate(Date.valueOf(LocalDate.of(2022,5,9)))
                .build();
        booksJDBCRepository.createBook(bookDetails);
    }

    private static void testDeleteBookById(BooksJDBCRepository booksJDBCRepository, int bookId) throws SQLException {
        booksJDBCRepository.deleteBookById(bookId);
    }

    private static void testGetAllBooks(BooksJDBCRepository booksRepository) throws SQLException {
        List<BookBasicInfo> allBooks = booksRepository.getAllBooks();
        allBooks.forEach(LibraryMain::logBookBasicInfo);
    }

    private static void testGetBookById(BooksJDBCRepository booksRepository, int requestedBookId) throws SQLException {
        var bookBasicInfoOpt = booksRepository.getBookBasicInfoById(requestedBookId);
        var bookBasicInfo = bookBasicInfoOpt.orElseThrow(
                () -> new IllegalStateException(String.format("Could not found book with id %s", requestedBookId)));
        logBookBasicInfo(bookBasicInfo);
    }

    private static void testUpdateBookTitle(BooksJDBCRepository booksRepository, int requestedBookId) throws SQLException {
        var newTitle = "Updated: Mistrz czystego kodu. Kodeks postępowania profesjonalnych programistów";
        booksRepository.updateBookTitle(newTitle, requestedBookId);

    }

    private static void logBookBasicInfo(BookBasicInfo bookBasicInfo) {
        log.info("Found book: id: {}, title: {}", bookBasicInfo.getId(), bookBasicInfo.getTitle());
    }

    private static void testUpdateBook(BooksJDBCRepository booksJDBCRepository) throws SQLException {
        var bookDetails = BookDetails.builder()
                .id(5)
                .title("New title")
                .authorId(5)
                .categoryId(5)
                .publisher("New publisher")
                .releaseDate(Date.valueOf(LocalDate.of(2022,5,5)))
                .build();
        booksJDBCRepository.updateBook(bookDetails);
    }

    private static void testGetBooksCount(BooksJDBCRepository booksJDBCRepository) throws SQLException {
        long booksCount = booksJDBCRepository.getBooksCount();
        log.info("Number of all books: {}", booksCount);
    }
}










