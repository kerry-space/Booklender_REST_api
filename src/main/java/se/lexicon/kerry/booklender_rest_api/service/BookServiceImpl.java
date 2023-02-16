package se.lexicon.kerry.booklender_rest_api.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.kerry.booklender_rest_api.exception.DataNotFoundException;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.BookDto;
import se.lexicon.kerry.booklender_rest_api.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<BookDto> findByReserved(boolean reserved) {
        List<Book> books = bookRepository.findByReservedStatus(reserved);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public List<BookDto> findByAvailable(boolean available) {
        List<Book> books = bookRepository.findByAvailableStatus(available);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public List<BookDto> findByBookTitle(String title) {
        List<Book> books = bookRepository.findAllByTitleContains(title);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public BookDto findById(Integer bookId) {
        if (bookId == null) throw new IllegalArgumentException("role id was null");
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            return modelMapper.map(bookOptional.get(), BookDto.class);
        } else {
            return null;
        }
    }

    public List<BookDto> findAll() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public BookDto create(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("Book data was null");
        if (bookDto.getBookId() != 0) throw new IllegalArgumentException("Book id should be null or zero");
        Book book = modelMapper.map(bookDto, Book.class);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }

    public BookDto update(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("Book data was null");

        if (bookDto.getBookId() == 0) throw new IllegalArgumentException("Book id should not be zero");


        Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
        if (!optionalBook.isPresent()) throw new DataNotFoundException("Book not found");
        Book entity = optionalBook.get();

        Book createdBook = bookRepository.save(entity); // saves the updated book entity to the database
        return modelMapper.map(createdBook, BookDto.class); // updates the fields of the book entity
    }

    @Override
    public Boolean delete(Integer bookId) {
        Book entity = bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException("Book  data WAS NOT FOUND"));
        bookRepository.delete(entity);
        return true;
    }

}


