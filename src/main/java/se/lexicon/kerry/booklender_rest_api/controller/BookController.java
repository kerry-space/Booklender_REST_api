package se.lexicon.kerry.booklender_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.kerry.booklender_rest_api.model.dto.BookDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;
import se.lexicon.kerry.booklender_rest_api.repository.BookRepository;
import se.lexicon.kerry.booklender_rest_api.repository.LibraryUserRepository;
import se.lexicon.kerry.booklender_rest_api.service.BookService;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    //inject BookServicein order to access it’s functionality
    @Autowired
    private BookService bookService;




    //CRUD http requests

    //Create(C)
    @PostMapping("/")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto){
       BookDto createdBookDto = bookService.create(bookDto);
       return ResponseEntity.status(HttpStatus.OK).body(createdBookDto);
    }

    //Read(R)
    @GetMapping("/")
    public ResponseEntity<List<BookDto>> findAll(){
      List<BookDto> bookDtoList = bookService.findAll();
      return ResponseEntity.status(HttpStatus.OK).body(bookDtoList);
    }


    @GetMapping("/reserved/{res}")
    public ResponseEntity<List<BookDto>>findByReserved(@PathVariable("res") boolean reserved){
        return ResponseEntity.status(HttpStatus.OK).body( bookService.findByReserved(reserved));
    }

    @GetMapping("/available/{avail}")
    public ResponseEntity<List<BookDto>>findByAvailable(@PathVariable("avail") boolean available){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByAvailable(available));
    }

    @GetMapping("/title/{tit}")
    public ResponseEntity<List<BookDto>>findByTitle(@PathVariable("tit") String title){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByTitle(title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto>findById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PutMapping("/")
    public ResponseEntity<BookDto>update(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(bookDto));
    }



    @DeleteMapping("/")
    public ResponseEntity<Boolean>delete(@RequestBody Integer bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.delete(bookId));
    }

}
