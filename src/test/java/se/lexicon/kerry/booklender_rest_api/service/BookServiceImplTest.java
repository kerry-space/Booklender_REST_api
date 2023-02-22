package se.lexicon.kerry.booklender_rest_api.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.kerry.booklender_rest_api.model.dto.BookDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceImplTest {

    @Mock
    BookService testObject;

    @Autowired
    ModelMapper modelMapper;

    @InjectMocks
    BookDto createdBook;


    @BeforeEach
    public void setup(){
        BookDto bookDto = new BookDto(1,"The Great Gatsby", false,true,10,new BigDecimal(12),"book is describing the existence of universe");

       createdBook = testObject.create(bookDto);
       assertNotNull(createdBook);
    }

    @Test
    public void testFindAll() {
        List<BookDto> books = testObject.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    public void testFindByTitle() {
        List<BookDto> books = testObject.findByTitle("The Great Gatsby");
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals(createdBook, books.get(0).getTitle());
    }

    @Test
    public void testFindById() {
        BookDto book = testObject.findById(createdBook.getBookId());
        assertNotNull(book);
        assertEquals(1, book.getBookId());

    }

    @Test
    public void testCreate() {
        Book entity = new Book("George Orwell", 10,new BigDecimal(12),"book is describing the art of loving");
        BookDto createdBook = testObject.create(modelMapper.map(entity, BookDto.class));
        assertNotNull(createdBook);
        assertNotNull(createdBook.getBookId());
        assertEquals("book is describing the art of loving", createdBook.getTitle());
        assertEquals("George Orwell", createdBook.getTitle());
        assertFalse(createdBook.isReserved());
    }

    @Test
    public void testUpdate() {
        BookDto book = testObject.findById(1);
        book.setReserved(true);
        BookDto updatedBook = testObject.update(book);
        assertNotNull(updatedBook);
        assertTrue(updatedBook.isReserved());
    }

    @Test
    public void testDelete() {
        boolean deleted = testObject.delete(1);
        assertTrue(deleted);
        assertNull(testObject.findById(1));
    }
}
