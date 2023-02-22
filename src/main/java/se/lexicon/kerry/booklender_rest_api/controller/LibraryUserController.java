package se.lexicon.kerry.booklender_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.kerry.booklender_rest_api.model.dto.LibraryUserDto;
import se.lexicon.kerry.booklender_rest_api.repository.LibraryUserRepository;
import se.lexicon.kerry.booklender_rest_api.service.LibraryUserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/library")
public class LibraryUserController {

    //inject LibraryUserServicein order to access it’s functionality
    @Autowired
    LibraryUserService libraryUserService;



    //CRUD http requests

    //Create(C)
    @PostMapping("/")
    public ResponseEntity<LibraryUserDto>create(@RequestBody LibraryUserDto libraryUserDto){
        LibraryUserDto createLibraryUserDto = libraryUserService.create(libraryUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createLibraryUserDto);
    }


    //Read(R)
    @GetMapping("/")
    public ResponseEntity<List<LibraryUserDto>>findAll(){
        List<LibraryUserDto> findLibraryUserDtoList = libraryUserService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(findLibraryUserDtoList);

    }

    //Read(R)
    @GetMapping("/email/{email}")
    public ResponseEntity<LibraryUserDto>findByEmail(@PathVariable("email") String email){
        LibraryUserDto findLibraryDto = libraryUserService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(findLibraryDto);
    }

    //Read(R)
    @GetMapping("/{id}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable("id") Integer id){
       LibraryUserDto findLibraryUserDto = libraryUserService.findById(id);
       return  ResponseEntity.status(HttpStatus.OK).body(findLibraryUserDto);
    }


    //Update(U)
    @PutMapping("/")
    public ResponseEntity<LibraryUserDto>update(@RequestBody LibraryUserDto libraryUserDto){
      LibraryUserDto updateLibraryUserDto =  libraryUserService.update(libraryUserDto);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateLibraryUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable("id") Integer userId){
        boolean deleteLibraryUser = libraryUserService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteLibraryUser);
    }

}
