package lv.twino;

import lv.twino.model.Client;
import lv.twino.model.Country;
import lv.twino.model.Loan;
import lv.twino.repository.ClientRepository;
import lv.twino.repository.CountryRepository;
import lv.twino.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanServiceTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LoanService loanService;

    @Test
    public void whenApplyForTheLoadThenSaveInDb() {
        Client client = this.clientRepository.save(new Client("Edgars", "Naglis"));
        Country country = this.countryRepository.save(new Country("Latvia"));
        Loan loan = this.loanService.apply(new Loan(new BigDecimal(1000), 60, country, client));
        List<Loan> result = this.loanService.getAll();
        assertTrue(result.contains(loan));
    }

    @Test
    public void whenFindByPersonThenReturnListOnlyForRerson() {
        Client client = this.clientRepository.save(new Client("Edgars", "Naglis"));
        Country country = this.countryRepository.save(new Country("Latvia"));
        Loan loan = this.loanService.apply(new Loan(new BigDecimal(1000), 60, country, client));
        List<Loan> result = this.loanService.getByClient(client.getId());

      //  assertThat(result.iterator().next(), is(loan));


    }
}
