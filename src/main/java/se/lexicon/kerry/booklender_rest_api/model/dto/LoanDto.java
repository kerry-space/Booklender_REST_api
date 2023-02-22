package se.lexicon.kerry.booklender_rest_api.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {
    private long loanId;

    private LibraryUserDto loanTaker;

    private BookDto book;

    private LocalDate loanDate;

    private boolean terminated;
}
