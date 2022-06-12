package pl.sda.library.hibernate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.sda.library.common.BookBasicInfo;
import pl.sda.library.common.BookDetails;
import pl.sda.library.common.BooksRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class BooksJpaRepository implements BooksRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<BookBasicInfo> getBookBasicInfoById(int id) throws SQLException {
        var selectBookBasicInfoById = """
            select new pl.sda.library.common.BookBasicInfo(bd.id, bd.title)
            from BookDetails bd
            where bd.id = :id
            """;
        var query = entityManager.createQuery(selectBookBasicInfoById, BookBasicInfo.class);
        query.setParameter("id", id);

        try {
            var bookBasicInfo = query.getSingleResult();
            return Optional.of(bookBasicInfo);
        } catch (NoResultException e) {
            log.warn("Could not find book by provided id: {}", id);
            return Optional.empty();
        }
    }

    @Override
    public List<BookBasicInfo> getAllBooks() throws SQLException {
        var selectAllBooks = """
                select new pl.sda.library.common.BookBasicInfo(bd.id, bd.title)
                from BookDetails bd
                """;
        var query = entityManager.createQuery(selectAllBooks, BookBasicInfo.class);
        return query.getResultList();
    }

    @Override
    public void deleteBookById(int id) throws SQLException {

        try {
            entityManager.getTransaction().begin();
            var selectedBookToRemove = "select bd from BookDetails bd where bd.id = :id";
            var query = entityManager.createQuery(selectedBookToRemove, BookDetails.class);
            query.setParameter("id", id);
            var bookDetails = query.getSingleResult();
            entityManager.remove(bookDetails);
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            log.warn("Cannot delete non-existing book. Book id: {}", id);
        }

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
        var countQuery = entityManager.createQuery("select count(bd.id) from BookDetails bd", Long.class);
        return countQuery.getSingleResult();
    }
}
