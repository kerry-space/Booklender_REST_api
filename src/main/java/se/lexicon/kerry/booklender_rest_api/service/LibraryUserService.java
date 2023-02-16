package se.lexicon.kerry.booklender_rest_api.service;

import se.lexicon.kerry.booklender_rest_api.model.entity.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {

    LibraryUserDto findById(Integer userId);
    LibraryUserDto findByEmailId(String email);
    List<LibraryUserDto> findAll();
    LibraryUserDto create(LibraryUserDto LibraryUserDto);
    void update(LibraryUserDto LibraryUserDto);
    void delete(boolean userId);
}
