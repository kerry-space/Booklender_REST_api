package se.lexicon.kerry.booklender_rest_api.service;

import se.lexicon.kerry.booklender_rest_api.model.dto.LoanDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.Loan;

import java.util.List;

public interface LoanService {
    LoanDto findById(Long loanId);

    List<LoanDto> findByBookId(Integer bookId);

    List<LoanDto> findByUserId(Integer userId);

    List<LoanDto> findByConcluded(boolean concluded);

    List<LoanDto> findAll();

    LoanDto create(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    boolean delete(Long loanId);
}
