package lv.twino.service;

import lv.twino.model.Client;
import lv.twino.model.Country;
import lv.twino.model.Loan;
import lv.twino.repository.LoanRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoanService {

    @Autowired
    private LoanRepository repository;


    public Loan apply(final Loan loan){
        return this.repository.save(loan);
    }

    public List<Loan> createLoans() {
        List<Loan> loans = new ArrayList<Loan>();
        List<Loan> savedLoans = new ArrayList<Loan>();

        Client client1 = new Client("Edgars", "Naglis");
        Client client2 = new Client("Rajesh", "Sharma");
        Client client3 = new Client("Rohit", "Chauhan");
        Country country = new Country("Latvija");

        loans.add(new Loan (new BigDecimal(1000), 60, country, client1));
        loans.add(new Loan (new BigDecimal(1000), 60, country, client2));
        loans.add(new Loan (new BigDecimal(1000), 60, country, client3));

        Iterable<Loan> itrLoans=repository.saveAll(loans);
        itrLoans.forEach(savedLoans::add);

        return savedLoans;
    }

    public List<Loan> getAll(){
        return Lists.newArrayList(this.repository.findAll());
    }

    public List<Loan> getByClient(Integer clientId){
        return this.repository.findByClient(new Client(clientId));
    }

 
}
