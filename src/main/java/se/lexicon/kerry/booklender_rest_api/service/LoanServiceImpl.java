package se.lexicon.kerry.booklender_rest_api.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.kerry.booklender_rest_api.exception.DataNotFoundException;
import se.lexicon.kerry.booklender_rest_api.model.entity.Loan;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.LoanDto;
import se.lexicon.kerry.booklender_rest_api.repository.LoanRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public LoanDto findById(long loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isPresent()) {
            return modelMapper.map(optionalLoan.get(), LoanDto.class);
        } else {
            throw new DataNotFoundException("Loan with id " + loanId + " not found");
        }
    }

    @Override
    public List<LoanDto> findByBookId(Integer bookId) {
        List<Loan> loanList = loanRepository.findByBookId(bookId);
        return loanList.stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findByUserId(Integer userId) {
        List<Loan> loanList = loanRepository.findByBookId(userId);
        return loanList.stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findByConcluded(boolean concluded) {
        List<Loan> loanList = loanRepository.findByConcluded(concluded);
        return loanList.stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> loanList = (List<Loan>) loanRepository.findAll();
        return loanList.stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        Loan loan = modelMapper.map(loanDto, Loan.class);
        Loan savedLoan = loanRepository.save(loan);
        return modelMapper.map(savedLoan, LoanDto.class);
    }


    @Override
    public LoanDto update(LoanDto loanDto) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanDto.getLoanId());
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            modelMapper.map(loanDto, loan);
            Loan updatedLoan = loanRepository.save(loan);
            return modelMapper.map(updatedLoan, LoanDto.class);
        } else {
            throw new DataNotFoundException("Loan with id " + loanDto.getLoanId() + " not found");
        }
    }

    @Override
    public Boolean delete(long loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isPresent()) {
            loanRepository.delete(optionalLoan.get());
            return true;
        } else {
            throw new DataNotFoundException("Loan with id " + loanId + " not found");
        }
    }
}




