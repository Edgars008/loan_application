package lv.twino.service;

import lv.twino.model.Client;
import lv.twino.model.Country;
import lv.twino.model.Loan;
import lv.twino.repository.ClientRepository;
import lv.twino.repository.CountryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest
public class LoanServiceTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LoanService loanService;

    @Test
    public void whenApplyForTheLoanThenSaveInDb() {
        Client client = this.clientRepository.save(new Client("Edgars", "Naglis"));
        Country country = this.countryRepository.save(new Country("Latvia"));
        Loan loan = this.loanService.apply(new Loan(new BigDecimal(1000), 60, country, client));
        List<Loan> result = this.loanService.getAll();
        assertTrue(result.contains(loan));
    }

    @Test
    public void whenFindByClientsIdThenReturnListOnlyForClient() {
        Client client = this.clientRepository.save(new Client("Edgars", "Naglis"));
        Country country = this.countryRepository.save(new Country("Latvia"));
        Loan loan = this.loanService.apply(new Loan(new BigDecimal(1000), 60, country, client));
        List<Loan> result = this.loanService.getByClient(client.getId());

        Assert.assertThat(result.iterator().next(), is(loan));


    }
}
