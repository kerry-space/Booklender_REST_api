package se.lexicon.kerry.booklender_rest_api.service;

import se.lexicon.kerry.booklender_rest_api.model.entity.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findByReserved(boolean reserved);
    List<BookDto> findByAvailable(boolean available);
    List<BookDto> findByBookTitle(String title);
    BookDto findById(Integer bookId);
    List<BookDto> findAll();
    BookDto create(BookDto BookDto);
    void update(BookDto BookDto);
    void delete(Integer bookId);
}
