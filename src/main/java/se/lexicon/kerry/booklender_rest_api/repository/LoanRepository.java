package se.lexicon.kerry.booklender_rest_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.kerry.booklender_rest_api.model.entity.Loan;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {


    Optional<Loan> findById(Long loanId);




    @Query("select l from Loan l where l.book.bookId = :bookId")
    Optional<Loan> findByBookId(@Param("bookId") int bookId);


    @Query("select l from Loan l where l.concluded = :concluded")
   List<Loan> findByConcluded(@Param("concluded") boolean concluded);




}
