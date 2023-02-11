package se.lexicon.kerry.booklender_rest_api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LibraryRepositoryTest {

    @Autowired
    LibraryUserRepository libraryUserRepository;

}
