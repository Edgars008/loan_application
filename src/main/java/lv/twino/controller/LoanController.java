package lv.twino.controller;

import lv.twino.model.Loan;
import lv.twino.service.BlackListService;
import lv.twino.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BlackListService blackListService;

 
    @PostMapping("/loans")
    public ResponseEntity<Void> createLoan(@RequestBody Loan loan) {

        if (!this.blackListService.isBlackListClient(loan.getClient().getId())){
            loanService.apply(loan);
        }else {
            String.format("User %s %s with id %s is in the Blacklist!",
                    loan.getClient().getName(),
                    loan.getClient().getSurname(),
                    loan.getClient().getId());
        }
      
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/loans")
    public List<Loan> getAll() {
        return loanService.getAll();

    }

    @GetMapping("/loans/{clientId}")
    public List<Loan> findByClientId(@PathVariable Integer clientId) {
        return this.loanService.getByClient(clientId);
       
    }

}
