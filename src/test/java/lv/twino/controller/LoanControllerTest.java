package lv.twino;


import com.fasterxml.jackson.databind.ObjectMapper;
import lv.twino.controller.LoanController;
import lv.twino.controller.apply_forms.ErrorResult;
import lv.twino.controller.apply_forms.SuccessResult;
import lv.twino.model.Client;
import lv.twino.model.Country;
import lv.twino.model.Loan;
import lv.twino.service.BlackListService;
import lv.twino.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlackListService blackListService;

    @MockBean
    private LoanService loanService;


    @Test
    public void whenClientIsNotInBlackListThenApplyLoan() throws Exception {

        List<Loan> list = Collections.singletonList(
                new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"))
        );
        ObjectMapper mapper = new ObjectMapper();
        given(this.loanService.getAll()).willReturn(list);

        this.mvc.perform(MockMvcRequestBuilders.get("/loans/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().
                        string(mapper.writeValueAsString(list))
        );
    }

    @Test
    public void whenLoadThenApplyForTheLoan() throws Exception {
        List<Loan> list = Collections.singletonList(
                new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"))
        );
        ObjectMapper mapper = new ObjectMapper();
        given(this.loanService.getByClient(0)).willReturn(list);

        this.mvc.perform(MockMvcRequestBuilders.get("/loans/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().
                        string(mapper.writeValueAsString(list))
                );
    }

    @Test
    public void whenApplyForTheLoanThanSave() throws Exception {
        Loan loan = new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"));
        ObjectMapper mapper = new ObjectMapper();

        given(this.blackListService.isBlackListClient(0)).willReturn(false);
        given(this.loanService.apply(loan)).willReturn(loan);

        this.mvc.perform(MockMvcRequestBuilders.post("/loans/add").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(loan)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().
                        string(mapper.writeValueAsString(new SuccessResult<Loan>(loan)))
                );

    }

    @Test
    public void whenClientIsInBlacklistThenError() throws Exception {

        Loan loan = new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"));
        ObjectMapper mapper = new ObjectMapper();

        given(this.blackListService.isBlackListClient(0)).willReturn(true);

        this.mvc.perform(MockMvcRequestBuilders.post("/loans/add").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(loan)
                       ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(mapper.writeValueAsString
                        (new ErrorResult("User Edgars Naglis with id 0 is in the blacklist!"))));
    }
}
