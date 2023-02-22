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



    @Query("select l from Loan l where l.loanTaker.userId = :userId")
    List<Loan> findByUserId(@Param("userId") int userId);




    @Query("select l from Loan l where l.book.bookId = :bookId")
    List<Loan> findByBookId(@Param("bookId") int bookId);


    @Query("select l from Loan l where l.concluded = :concluded")
   List<Loan> findByConcluded(@Param("concluded") boolean concluded);

    @Query("delete from Loan l where l.loanId = :loanId")
    boolean deleteByById(@Param("loanId") Long loanId);



}
