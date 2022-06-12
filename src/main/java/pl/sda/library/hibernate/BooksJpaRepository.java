package pl.sda.library.hibernate;

import pl.sda.library.common.BookBasicInfo;
import pl.sda.library.common.BookDetails;
import pl.sda.library.common.BooksRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BooksJpaRepository implements BooksRepository {
    @Override
    public Optional<BookBasicInfo> getBookBasicInfoById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<BookBasicInfo> getAllBooks() throws SQLException {
        return null;
    }

    @Override
    public void deleteBookById(int bookId) throws SQLException {

    }

    @Override
    public void updateBookTitle(String newBookTitle, int bookId) throws SQLException {

    }

    @Override
    public void createBook(BookDetails bookDetails) throws SQLException {

    }

    @Override
    public void updateBook(BookDetails bookDetails) throws SQLException {

    }

    @Override
    public long getBooksCount() throws SQLException {
        return 0;
    }
}
