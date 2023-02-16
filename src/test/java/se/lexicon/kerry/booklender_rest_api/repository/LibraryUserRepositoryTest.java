package se.lexicon.kerry.booklender_rest_api.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cglib.core.Local;
import se.lexicon.kerry.booklender_rest_api.model.entity.LibraryUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class LibraryUserRepositoryTest {

    @Autowired
    LibraryUserRepository testObject;

    LibraryUser createdLibraryUser;

    @BeforeEach
    public void setup(){
        LibraryUser libraryUser = new LibraryUser(LocalDate.now(),"FreeMind library", "mind@gmail.com");
        createdLibraryUser = testObject.save(libraryUser);

        assertNotNull(createdLibraryUser);
    }


    //CRUD TEST

    //Read(R)
    @Test
    public void test_findById(){
        LibraryUser actual= testObject.findById(createdLibraryUser.getUserId()).orElseThrow(()-> new IllegalArgumentException("created  libraryUser data not found"));
        LibraryUser expected = createdLibraryUser;
        assertEquals(expected,actual);
    }

    //Read(R)
    @Test
    public void findAllByOrderByUserIdDesc(){
        List<LibraryUser>  libraryUserList = testObject.findAllByOrderByUserIdDesc();
        assertNotNull(libraryUserList);

        assertEquals(1, libraryUserList.size());
    }

    //Read(R)
    @Test
    public void test_findByEmail(){
        Optional<LibraryUser> optionalLibraryUser = testObject.findByEmail(createdLibraryUser.getEmail());
        assertTrue(optionalLibraryUser.isPresent());

        LibraryUser actual = optionalLibraryUser.get();
        LibraryUser expected = createdLibraryUser;

        assertEquals(expected, actual);
    }


    //Read(R)
    @Test
    public void test_selectByRegistrationDate(){
        List<LibraryUser> libraryUserList = testObject.selectByRegistrationDate(LocalDate.now(), LocalDate.parse("2023-02-13"));
        assertNotNull(libraryUserList);

        assertEquals(1,libraryUserList.size());
    }

    //Update(U)
    @Test
    public void test_update_email(){
        //step1: the expected object
        LibraryUser expected = createdLibraryUser;

        //step2: find object and update it
        LibraryUser findedLibraryUser = testObject.findById(createdLibraryUser.getUserId()).orElseThrow(() -> new IllegalArgumentException("data was not found"));

        findedLibraryUser.setEmail("love@gmail.com");

        //S
        // step3: save to database the update object
        LibraryUser actual = testObject.save(findedLibraryUser);

        //step4: test
        assertEquals(expected, actual);
    }

    //Delete(D)
    @Test
    public void test_delete_libraryUser(){
        testObject.delete(createdLibraryUser);

        assertEquals(0,testObject.count());
    }


}
