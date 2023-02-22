package se.lexicon.kerry.booklender_rest_api.service;

import org.springframework.data.jpa.repository.Query;
import se.lexicon.kerry.booklender_rest_api.model.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById(Integer userId);

    LibraryUserDto findByEmail(String email);

    List<LibraryUserDto> findAll();

    LibraryUserDto create(LibraryUserDto libraryUserDto);

    LibraryUserDto update(LibraryUserDto libraryUserDto);


    boolean delete(Integer userId);
}
