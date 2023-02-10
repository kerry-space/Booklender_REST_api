package se.lexicon.kerry.booklender_rest_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.kerry.booklender_rest_api.model.entity.LibraryUser;

import java.util.Optional;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {
    @Query("select e from LibraryUser e where e.email= :el")
    Optional<LibraryUser> findAllByEmailIgnoreCase(@Param("el") String email);

}
