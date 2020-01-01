package lv.twino.controller;

import lv.twino.repository.ClientRepository;
import lv.twino.repository.CountryRepository;
import lv.twino.service.LoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerMockmvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanController loanController;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private LoanService loanService;



    @Test
    public void testCreateRetrieveWithMockMVC() throws Exception {

//        Client client = this.clientRepository.save(new Client("Edgars", "Naglis"));
//        Country country = this.countryRepository.save(new Country("Latvia"));
//        Loan loan = this.loanService.apply(new Loan(new BigDecimal(1000), 60, country, client));
//        List<Loan> result = this.loanService.getAll();

        this.mockMvc.perform(post("/loans/all")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/loans/1")).andDo(print()).andExpect(status().isOk())
          .andExpect(content().string(containsString("Edgars")));
    }
 
}

