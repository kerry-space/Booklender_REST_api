package se.lexicon.kerry.booklender_rest_api.service;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.LoanDto;

import java.util.List;

public interface LoanService {
    LoanDto findById(long loanId);
    List<LoanDto> findByBookId(Integer bookId);
    List<LoanDto> findByUserId(Integer userId);
    List<LoanDto> findByConcluded(boolean loanId);
    List<LoanDto> findAll();
    LoanDto create(LoanDto LoanDto);
    LoanDto update(LoanDto LoanDto);
    Boolean delete(long loanId);
}
