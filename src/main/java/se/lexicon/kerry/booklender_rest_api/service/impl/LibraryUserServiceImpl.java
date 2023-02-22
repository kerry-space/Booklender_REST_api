package se.lexicon.kerry.booklender_rest_api.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.kerry.booklender_rest_api.exception.DataDublicateException;
import se.lexicon.kerry.booklender_rest_api.exception.DataNotFoundException;
import se.lexicon.kerry.booklender_rest_api.model.dto.LibraryUserDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.LibraryUser;
import se.lexicon.kerry.booklender_rest_api.repository.LibraryUserRepository;
import se.lexicon.kerry.booklender_rest_api.service.LibraryUserService;

import java.util.Iterator;
import java.util.List;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public LibraryUserDto findById(Integer userId) {
        //check param
        if (userId == null)throw new IllegalArgumentException("The user id parameter was null!");
        //find
        LibraryUser entity = libraryUserRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("libraryUser id was not valid!"));
        //convert to dto
      return modelMapper.map(entity, LibraryUserDto.class);

    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        //validate param
        if (email == null) throw new IllegalArgumentException("the email parameter is null!");
        //find
       LibraryUser entity = libraryUserRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("The email was not valid!"));
        //convert to dto
        return modelMapper.map(entity, LibraryUserDto.class);
    }

    @Override
    public List<LibraryUserDto> findAll() {
        //find
       Iterable<LibraryUser> entity =  libraryUserRepository.findAll();
       //convert to dto
        return modelMapper.map(entity, new TypeToken<List<LibraryUserDto>>(){}.getType());
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        //validate parameter
       // if (libraryUserDto == null)throw new IllegalArgumentException("the library user parameter is null!");
       // if (libraryUserDto.getUserId() == 0) throw new IllegalArgumentException("libraryUser id should not be null or zero!");

        //check email that should not be duplicated
        if (libraryUserRepository.findByEmail(libraryUserDto.getEmail()).isPresent()) throw new DataDublicateException("the email name is duplicate!");

       //save entity first convert to entity
       LibraryUser createdEntity = libraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
       //convert back to dto
        return modelMapper.map(createdEntity, LibraryUserDto.class);
    }

    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) {
        //validate parameter
        if (libraryUserDto == null)throw new IllegalArgumentException("THe libraryUser data  was null!");
        if (libraryUserDto.getUserId() == 0)throw new IllegalArgumentException("the id doesn't exist");

        if (!libraryUserRepository.findById(libraryUserDto.getUserId()).isPresent())
            throw new DataNotFoundException("Data not found");

        if (libraryUserRepository.findByEmail(libraryUserDto.getEmail()).isPresent())
            throw new DataDublicateException("duplicate error exception!");


       //save but before convert to entity
       LibraryUser foundLibraryUser = libraryUserRepository.save(modelMapper.map(libraryUserDto, LibraryUser.class));
       //convert back dto return
       return modelMapper.map(foundLibraryUser, LibraryUserDto.class);
    }

    @Override
    public boolean delete(Integer userId) {
        //find by id
        LibraryUser libraryUser = libraryUserRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("the id wass not valid "));
        libraryUserRepository.delete(libraryUser);
        return true;
    }
}
