package se.lexicon.kerry.booklender_rest_api.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.kerry.booklender_rest_api.exception.DataDublicateException;
import se.lexicon.kerry.booklender_rest_api.exception.DataNotFoundException;
import se.lexicon.kerry.booklender_rest_api.model.dto.BookDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;
import se.lexicon.kerry.booklender_rest_api.repository.BookRepository;
import se.lexicon.kerry.booklender_rest_api.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
      //find
        List<Book> bookList = bookRepository.findByReservedStatus(reserved);
        //validate
        if (bookList.isEmpty())throw new DataNotFoundException("the book data was not found by reserved status");

        //covert to dto and return
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        //find
        List<Book> bookList = bookRepository.findByAvailableStatus(available);
        //validate
        if (bookList.isEmpty())throw new DataNotFoundException("the book data was not found by available status");

        //convert to dto and return
        return modelMapper.map(bookList,new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        //validate method parameter
        if (title == null)throw new IllegalArgumentException("the book title parameter is null!");
        //find
        List<Book> bookList = bookRepository.findAllByTitleContains(title);
        //validate
        if (bookList.isEmpty())throw new DataNotFoundException("the book data was not found by title ");

        //convert to dto and return
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public BookDto findById(Integer bookId) {
        //validate method param
        if (bookId == null)throw new IllegalArgumentException("the book id parameter is null!");
        //find
        Book entity = bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException("book id was not valid!"));

        //convert to dto and return
        return modelMapper.map(entity, BookDto.class);
    }

    @Override
    public List<BookDto> findAll() {
      Iterable<Book> entity =  bookRepository.findAll();
      //convert to dto and return
        return modelMapper.map(entity, new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public BookDto create(BookDto bookDto) {
        //validate method param
        if (bookDto == null)throw new IllegalArgumentException("The book parameter data was not found!");
        if (bookDto.getBookId() != 0)throw new IllegalArgumentException("The book id should not be zero or null");


       /*if (!bookRepository.findById(bookDto.getBookId()).isPresent())
           throw new DataNotFoundException("not found book data");*/
        if (!bookRepository.findAllByTitleContains(bookDto.getTitle()).isEmpty())
            throw new DataDublicateException("the book already exist");


       //save
        Book entity = bookRepository.save(modelMapper.map(bookDto, Book.class));
        //convert back to dto and send
        return modelMapper.map(entity, BookDto.class);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        //validate method parameter
        if (bookDto == null)throw new IllegalArgumentException("the book parameter data was null!");
        if (bookDto.getBookId() == 0)throw new IllegalArgumentException("THE book id should not be zero or null");
        //find
        if (!bookRepository.findById(bookDto.getBookId()).isPresent())
            throw new DataNotFoundException("the data was not found");
       //update
        Book entity = bookRepository.save(modelMapper.map(bookDto, Book.class));
        //convert back to dto and return
        return modelMapper.map(entity, BookDto.class);
    }

    @Override
    public boolean delete(Integer bookId) {
        //validate method param
        if (bookId == null)throw new IllegalArgumentException("the book id parameter was null!");
        //validate find
        if(!bookRepository.findById(bookId).isPresent())
            throw new DataNotFoundException("the book data was not found");
        //delete successfully
        bookRepository.deleteById(bookId);
        return true;
    }
}
