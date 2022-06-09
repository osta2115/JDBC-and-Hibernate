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
        try(var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "admin", "Password1")) {
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
            testDeleteBookById(booksRepository, 51);

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
}









