package se.lexicon.kerry.booklender_rest_api.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.kerry.booklender_rest_api.exception.DataNotFoundException;
import se.lexicon.kerry.booklender_rest_api.model.dto.LoanDto;
import se.lexicon.kerry.booklender_rest_api.model.entity.Loan;
import se.lexicon.kerry.booklender_rest_api.repository.LoanRepository;
import se.lexicon.kerry.booklender_rest_api.service.LoanService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;


    @Autowired
    ModelMapper modelMapper;

    @Override
    public LoanDto findById(Long loanId) {
        //valid para
        if (loanId == null) throw new IllegalArgumentException("loan id was null!");
        //find loan create listLoan and convert to loan to loanDto by modelMapper.map
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("The loan id was not valid"));
        LoanDto loanDto = modelMapper.map(loan, LoanDto.class);
        return loanDto;
    }

    @Override
    public List<LoanDto> findByBookId(Integer bookId) {
        //check param
        if (bookId == null) throw new IllegalArgumentException("The book id param is null!");


        List<Loan> loanList =  loanRepository.findByBookId(bookId);

       //convert to list of LoanDto and return
        return modelMapper.map(loanList, new TypeToken<List<LoanDto>>(){}.getType());

    }

    @Override
    public List<LoanDto> findByUserId(Integer userId) {
        //validate para
        if (userId == null) throw new IllegalArgumentException("The user id was null!");

        //find by user id
       List<Loan> loanList = loanRepository.findByUserId(userId);

       //convert from entity to dto and return
      return  modelMapper.map(loanList, new TypeToken<List<LoanDto>>(){}.getType());

    }

    @Override
    public List<LoanDto> findByConcluded(boolean concluded) {
        //find iit
        List<Loan>  loanList= loanRepository.findByConcluded(concluded);
       //validate
        if (loanList.isEmpty()) throw new IllegalArgumentException("the loan list is empty");

        //convert entity tp dto and return
        return modelMapper.map(loanList,  new TypeToken<List<LoanDto>>(){}.getType());

    }

    @Override
    public List<LoanDto> findAll() {
       Iterable<Loan> loanList = loanRepository.findAll();
       return modelMapper.map(loanList, new TypeToken<LoanDto>(){}.getType());

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public LoanDto create(LoanDto loanDto) {
        //check method parameter
        if (loanDto == null) throw new IllegalArgumentException("the loanDto object was null");
        if (loanDto.getLoanId() == 0)throw new IllegalArgumentException("the loan id should not be zero or null");
        //check book data
        if (loanDto.getBook() == null)throw new IllegalArgumentException("does not have book data");
        if (loanDto.getBook().getBookId() == 0)throw new IllegalArgumentException("the book id should not be zero or null");

        //check libraryUser data
        if (loanDto.getLoanTaker() == null)throw new IllegalArgumentException("does not have LibraryUser data");
        if (loanDto.getLoanTaker().getUserId() == 0)throw new IllegalArgumentException("The LibraryUser id should not be zero or null");

        //convert dto to entity
       Loan entity = modelMapper.map(loanDto, Loan.class);
       //save to db
        Loan saveLoan = loanRepository.save(entity);
        //convert back to dto
        return modelMapper.map(saveLoan, LoanDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public LoanDto update(LoanDto loanDto) {
        //validate param
        if (loanDto == null)throw new IllegalArgumentException("the loan was null");
        if (loanDto.getLoanId() == 0)throw new IllegalArgumentException("the id should not be null or zero");



        //convert dto to entity
        Loan entity = modelMapper.map(loanDto, Loan.class);
        //update
       Loan findedLoan = loanRepository.findById(entity.getLoanId()).orElseThrow(() -> new IllegalArgumentException("the loan id was not valid!"));
       //update
       findedLoan.setLoanDate(entity.getLoanDate());
       findedLoan.setLoanTaker(entity.getLoanTaker());
       findedLoan.setBook(entity.getBook());
       findedLoan.setConcluded(entity.isConcluded());
       //save db
       Loan loan = loanRepository.save(findedLoan);
       //convert back to dto
        return modelMapper.map(findedLoan, LoanDto.class);
    }

    @Override
    public boolean delete(Long loanId) {
        //validate param
        if (loanId == null) throw new IllegalArgumentException("the loanId param was null!");
        //delete
       return  loanRepository.deleteByById(loanId);
    }
}
