package se.lexicon.kerry.booklender_rest_api.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cglib.core.Local;
import se.lexicon.kerry.booklender_rest_api.model.entity.Book;
import se.lexicon.kerry.booklender_rest_api.model.entity.LibraryUser;
import se.lexicon.kerry.booklender_rest_api.model.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    LoanRepository testObject;

    Loan createdLoan;
    @BeforeEach
    public void setup(){

        LibraryUser loanTaker = new LibraryUser(LocalDate.parse("2023-02-10"),"kerry","kerry@gmail.com");
        Book book = new Book("master the moment ",10,new BigDecimal(100),"master the moment is book that describe all moment im greateful in life");
        Loan loan = new Loan(loanTaker,book,LocalDate.now(),true);
        createdLoan = testObject.save(loan);
        assertNotNull(createdLoan);
    }

    //CRUD TEST

    //Create(C)
    @Test
    public void test_create_loan(){
        LibraryUser loanTaker = new LibraryUser(LocalDate.parse("2023-02-10"),"sandra","sandra@gmail.com");
        Book book = new Book("life's beauties ",30,new BigDecimal(200),"I have passion of beauties of my own soul");
        Loan loan = new Loan(loanTaker,book,LocalDate.now(),true);

        Loan actual = testObject.save(loan);
        Loan expected = loan;
        assertEquals(expected, actual);
    }


    //Read(R)
    @Test
    public void  test_findById(){
        Loan actual =  testObject.findById(createdLoan.getLoanId()).orElseThrow(() -> new IllegalArgumentException("data was not found provide id!"));
        Loan expected = createdLoan;

        assertEquals(expected, actual);
    }

    @Test
    public  void ConcludedStatus(){
        List<Loan> loan  =  testObject.findByConcluded(createdLoan.isConcluded());
        assertNotNull(loan);


        assertEquals(1, loan.size());
    }


    //Update(U)
    @Test
    public void test_update_loan(){
        //step1: expected result
        Loan expected = createdLoan;

        //step2: findById
        Loan findLoan = testObject.findById(createdLoan.getLoanId()).orElseThrow(() -> new IllegalArgumentException("could not find by id"));
        //step3: and update each object field individually by set and save update to database
        findLoan.getBook().setTitle("masterMind");
        findLoan.getBook().setMaxLoanDays(20);
        Loan actual = testObject.save(findLoan);

        //step3: compare result
        assertEquals(expected, actual);
    }

    @Test
    public void test_delete_loan(){
        testObject.delete(createdLoan);
        long actual  = 0;
        long expected = testObject.count();
        assertEquals(expected,actual);
    }


}
