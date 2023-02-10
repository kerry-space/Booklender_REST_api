package se.lexicon.kerry.booklender_rest_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.kerry.booklender_rest_api.model.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Long> {


    Optional<Loan> findById(Long loanId);



    @Query("select b from Book b where b.bookId = :bookId")
    boolean findByBookId(@Param("bookId") Long bookId);

    @Query("select l from Loan l where l.concluded = :ConludedStatus")
    List<Loan> findByConludedStatus(@Param("ConludedStatus") boolean ConludedStatus);

}
