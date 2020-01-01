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
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BlackListService blackListService;

 
    @PostMapping(value = "/add")
    public ResponseEntity<Void> apllyForTheLoan(@RequestBody final Loan loan) {

        if (!this.blackListService.isBlackListClient(loan.getClient().getId())){
            this.loanService.apply(loan);
        }else {
            String.format("User %s %s with id %s is in the blacklist!",
                    loan.getClient().getName(),
                    loan.getClient().getSurname(),
                    loan.getClient().getId());
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();

    }


    @GetMapping(value = "/all")
    public List<Loan> getAll() {
        return loanService.getAll();

    }

//    @PostMapping(value = "/all")
//    public List<Loan> getAll() {
//        return loanService.createLoans();
//
//    }

    @GetMapping(value = "/{clientId}")
    public List<Loan> findByClientId(@PathVariable Integer clientId) {
        return loanService.getByClient(clientId);

    }

}
