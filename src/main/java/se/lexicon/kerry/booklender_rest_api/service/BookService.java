package se.lexicon.kerry.booklender_rest_api.service;

import se.lexicon.kerry.booklender_rest_api.model.dto.BookDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;

import java.util.List;

public interface BookService {

    List<BookDto> findByReserved(boolean reserved);

    List<BookDto> findByAvailable(boolean available);

    List<BookDto> findByTitle(String title);

    BookDto findById(Integer bookId);

    List<BookDto> findAll();

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    boolean delete(Integer bookId);
}
