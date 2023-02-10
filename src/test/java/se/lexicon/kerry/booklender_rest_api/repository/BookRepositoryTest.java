package se.lexicon.kerry.booklender_rest_api.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book("Test Book", 14, BigDecimal.valueOf(2.5), "This is a test book description.");
        bookRepository.save(testBook);
    }

    @Test
    void given_book_exists_when_findByTitleContains_then_return_book() {
        List<Book> books = bookRepository.findAllByTitleContains("Test");
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    void given_book_exists_when_findByAvailableStatus_then_return_book() {
        List<Book> books = bookRepository.findByAvailableStatus(true);
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }
}
