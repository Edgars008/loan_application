package lv.twino.service;

import lv.twino.model.Client;
import lv.twino.model.Loan;
import lv.twino.repository.LoanRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanService {

    @Autowired
    private LoanRepository repository;


    public Loan apply(final Loan loan){
        return this.repository.save(loan);
    }

    public List<Loan> getAll(){
        return Lists.newArrayList(this.repository.findAll());
    }

    public List<Loan> getByClient(Integer clientId){
        return this.repository.findByClient(new Client(clientId));
    }

 
}
