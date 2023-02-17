package se.lexicon.kerry.booklender_rest_api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.kerry.booklender_rest_api.model.entity.dto.LoanDto;
import se.lexicon.kerry.booklender_rest_api.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> find(@RequestParam(value = "userId", required = false) Integer userId,
                                              @RequestParam(value = "bookId", required = false) Integer bookId,
                                              @RequestParam(value = "status", required = false) String status) {
        List<LoanDto> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable("id") Long id) {
        LoanDto loan = loanService.findById(id);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/create")
    public ResponseEntity<LoanDto> create(@RequestBody LoanDto loanDto) {
        LoanDto createdLoan = loanService.create(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }

    @PutMapping("/update")
    public ResponseEntity<LoanDto> update( @RequestBody LoanDto loanDto) {
        LoanDto updatedLoan = loanService.update(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedLoan);
    }
    }
