package se.lexicon.kerry.booklender_rest_api.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.kerry.booklender_rest_api.model.entity.LibraryUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {


    Optional<LibraryUser> findById(Integer userId);

    List<LibraryUser> findAllByOrderByUserIdDesc();

    @Query("select l from LibraryUser l where l.email = :email")
    Optional<LibraryUser> findByEmail (@Param("email") String email);

    @Query("select l from LibraryUser l where l.regDate >= :from and l.regDate <= :to")
    List<LibraryUser> selectByRegistrationDate(@Param("from") LocalDate from, @Param("to") LocalDate to);

    /*@Modifying
    @Query("update LibraryUser l set l.name = :newName where l.name = :name")
    void updateName (@Param("name") String name ,@Param("newName") String newName);
    */
}
