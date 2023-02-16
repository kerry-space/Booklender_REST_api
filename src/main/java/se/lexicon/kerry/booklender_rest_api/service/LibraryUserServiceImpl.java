package se.lexicon.kerry.booklender_rest_api.service;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.kerry.booklender_rest_api.exception.DataDuplicateException;
import se.lexicon.kerry.booklender_rest_api.exception.DataNotFoundException;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;
import se.lexicon.kerry.booklender_rest_api.model.entity.LibraryUser;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.BookDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.LibraryUserDto;
import se.lexicon.kerry.booklender_rest_api.repository.LibraryUserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryUserServiceImpl implements LibraryUserService{

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public LibraryUserDto findById(Integer userId) {
        LibraryUser libraryUser = libraryUserRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found with id " + userId));
        return modelMapper.map(libraryUser, LibraryUserDto.class);
    }

    @Override
    public LibraryUserDto findByEmailId(String email) {
        LibraryUser libraryUser = libraryUserRepository.findByEmail(email)
                .orElseThrow(() ->  new DataNotFoundException("User not found with email " + email));
        return modelMapper.map(libraryUser, LibraryUserDto.class);
    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> libraryUsers = (List<LibraryUser>) libraryUserRepository.findAll();
        return libraryUsers.stream()
                .map(libraryUser -> modelMapper.map(libraryUser, LibraryUserDto.class))
                .collect(Collectors.toList());
    }



    @Override
    public LibraryUserDto create(LibraryUserDto LibraryUserDto) {
        if (libraryUserRepository.findByEmail(LibraryUserDto.getEmail()).isPresent()) {
            throw new DataDuplicateException("User with email " + LibraryUserDto.getEmail() + " already exists");
        }
        LibraryUser libraryUser = modelMapper.map(LibraryUserDto, LibraryUser.class);
        libraryUser = libraryUserRepository.save(libraryUser);
        return modelMapper.map(libraryUser, LibraryUserDto.class);
    }

    @Override
    public LibraryUserDto update(LibraryUserDto LibraryUserDto) {
        LibraryUser existingLibraryUser = libraryUserRepository.findById(LibraryUserDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id " + LibraryUserDto.getUserId()));
        if (!existingLibraryUser.getEmail().equals(LibraryUserDto.getEmail())
                && libraryUserRepository.findByEmail(LibraryUserDto.getEmail()).isPresent()) {
            throw new DataDuplicateException("User with email " + LibraryUserDto.getEmail() + " already exists");
        }
        modelMapper.map(LibraryUserDto, existingLibraryUser);
        existingLibraryUser = libraryUserRepository.save(existingLibraryUser);
        return modelMapper.map(existingLibraryUser, LibraryUserDto.class);
    }

    @Override
    public Boolean delete(Integer userId) {
        Optional<LibraryUser> userToDelete = libraryUserRepository.findById(userId);
        if (userToDelete.isPresent()) {
            libraryUserRepository.delete(userToDelete.get());
            return true;
        } else {
            throw new DataNotFoundException("User not found with id " + userId);
        }
    }
}
