package lv.twino.controller;

import lv.twino.model.Client;
import lv.twino.model.Country;
import lv.twino.model.Loan;
import lv.twino.repository.LoanRepository;
import lv.twino.service.LoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerMockTests {


    @Autowired
    private LoanService loanService;
    
    @MockBean
    private LoanRepository loanRepository;
 
    @Test
    public void testRetrieveLoanWithMockRepository() throws Exception {

        Client client = new Client("Edgars", "Naglis");
        Country country = new Country("Latvija");

        Optional<Loan> optLoan = Optional.of( new Loan
                (new BigDecimal(1000), 60, country, client));

        when(loanRepository.findById(1)).thenReturn(optLoan);

        assertTrue(loanService.getByClient(1).contains("Edgars"));
    }
 
}

