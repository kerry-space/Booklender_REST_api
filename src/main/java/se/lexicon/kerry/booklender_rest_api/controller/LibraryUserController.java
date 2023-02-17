package se.lexicon.kerry.booklender_rest_api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.LibraryUserDto;
import se.lexicon.kerry.booklender_rest_api.service.LibraryUserService;

import java.util.List;

@RequestMapping("/api/v1/libraryUser")
@RestController
public class LibraryUserController {
    @Autowired
    LibraryUserService libraryUserService;

    // http://localhost:8080/api/v1/libraryUser/{id}
    @GetMapping("/{id}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(libraryUserService.findById(userId));
    }

    // http://localhost:8080/api/v1/libraryUser/email
    @GetMapping("/email")
    public ResponseEntity<LibraryUserDto> findByEmail(@RequestParam("emailId") String emailId) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.findByEmailId(emailId));
    }

    // http://localhost:8080/api/v1/libraryUser/all
    @GetMapping("/all")
    public ResponseEntity<List<LibraryUserDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.findAll());
    }

    // http://localhost:8080/api/v1/libraryUser/create
    @PostMapping("/create")
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto dto){
        LibraryUserDto createdLibraryUserDto = libraryUserService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibraryUserDto); // 201
    }

    // http://localhost:8080/api/v1/libraryUser/update
    @PutMapping("/update")
    public ResponseEntity<LibraryUserDto> update(@RequestBody LibraryUserDto dto){
        LibraryUserDto updateLibraryUserDto = libraryUserService.update(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateLibraryUserDto);
    }

}


