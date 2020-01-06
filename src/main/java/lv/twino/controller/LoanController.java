package lv.twino.controller;

import lv.twino.controller.apply_forms.ApplyResult;
import lv.twino.controller.apply_forms.ErrorResult;
import lv.twino.controller.apply_forms.SuccessResult;
import lv.twino.model.Loan;
import lv.twino.service.BlackListService;
import lv.twino.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BlackListService blackListService;
 
    @PostMapping(value = "/add")
    public ApplyResult apllyForTheLoan(@RequestBody Loan loan) {
     final ApplyResult applyResult;
        if (!this.blackListService.isBlackListClient(loan.getClient().getId())){
            applyResult = new SuccessResult<Loan>(this.loanService.apply(loan));
        }
        else {
            applyResult = new ErrorResult(
                    String.format("User %s %s with id %s is in the blacklist!",
                    loan.getClient().getName(),
                    loan.getClient().getSurname(),
                    loan.getClient().getId()));
        }
        return applyResult;
    }

    @GetMapping(value = "/all")
    public List<Loan> getAll() {
        return loanService.getAll();
    }

    @GetMapping(value = "/{clientId}")
    public List<Loan> findByClientId(@PathVariable Integer clientId) {
        return loanService.getByClient(clientId);
    }

}
