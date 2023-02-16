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
    BookRepository testObject;

    Book createdBook;

    @BeforeEach
    public void setUp() {
        Book book = new Book("Test Book", 14, BigDecimal.valueOf(2.5), "This is a test book description.");
        book.setAvailable(true);
        createdBook = testObject.save(book);
        assertNotNull(createdBook);
    }

    //CRUD TEST

    //Create(C)
    @Test
    public void test_create_Book(){
        Book book = new Book("World of wonder",10,BigDecimal.valueOf(3.6),"The book world of wonder is book talking about minds wonder about the world");

        Book actual = testObject.save(book);
        Book expected = book;
        assertEquals(expected,actual);
    }


    //Read(R)
    @Test
    public void given_book_exists_when_findByTitleContains_then_return_book() {
        List<Book> books = testObject.findAllByTitleContains("Test");
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    //Read(R)
    @Test
    public void given_book_exists_when_findByAvailableStatus_then_return_book() {
        List<Book> books = testObject.findByAvailableStatus(true);
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    //Update(U)
    @Test
    public void test_update_maxLoanDays(){
        //step1: expected object
        Book expected = createdBook;

        //step2: find object by id and update it
        Book findedBook = testObject.findById(createdBook.getBookId()).orElseThrow(() -> new IllegalArgumentException("the book id could not be found"));
        findedBook.setMaxLoanDays(33);

        Book actual = testObject.save(findedBook);

        //step3: test object
        assertEquals(expected,actual);

    }


    //Delete(D)
    @Test
    public void test_delete_Book(){
        testObject.delete(createdBook);

        long expected = 0;
        long actual = testObject.count();
        assertEquals(expected,actual);

    }
}