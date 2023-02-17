package se.lexicon.kerry.booklender_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.BookDto;
import se.lexicon.kerry.booklender_rest_api.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Integer id) {
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        BookDto createdBook = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Integer id, @RequestBody BookDto bookDto) {
        BookDto updatedBook = bookService.update(bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> find(@RequestParam(value = "title", required = false, defaultValue = "all") String title,
                                                   @RequestParam(value = "available", required = false, defaultValue = "all") String available,
                                                   @RequestParam(value = "reserved", required = false, defaultValue = "all") String reserved,
                                                   @RequestParam(value = "all", required = false, defaultValue = "all") String all) {
        List<BookDto> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }
}


