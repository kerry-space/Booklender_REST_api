package se.lexicon.kerry.booklender_rest_api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;


    @ManyToOne()
    private LibraryUser loanTaker;

   private Book book;

   private LocalDate loanDate;

   private boolean concluded;

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean concluded) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.concluded = concluded;
    }
}
