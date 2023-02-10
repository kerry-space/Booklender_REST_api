package se.lexicon.kerry.booklender_rest_api.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;
import java.util.List;


@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {

    @Query("SELECT b FROM Book b WHERE b.reserved = :reserved")
    List<Book> findByReservedStatus(@Param("reserved") boolean reserved);

    @Query("SELECT b FROM Book b WHERE b.available = :available")
    List<Book> findByAvailableStatus(@Param("available") boolean available);
    List<Book> findAllByTitleContains(String title);
}

