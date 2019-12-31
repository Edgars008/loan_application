//package lv.twino;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lv.twino.controller.LoanController;
//import lv.twino.model.Client;
//import lv.twino.model.Country;
//import lv.twino.model.Loan;
//import lv.twino.service.BlackListService;
//import lv.twino.service.LoanService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(LoanController.class)
//public class LoanControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private BlackListService blackListService;
//
//    @MockBean
//    private LoanService loanService;
//
//
//
//    @Test
//    public void whenClientNotInBlackListThenApplyLoan() throws Exception {
//
//        List<Loan> list = Collections.singletonList(
//                new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"))
//        );
//
//
//        ObjectMapper mapper = new ObjectMapper();
//
//
//      this.loanService.getAll()).willReturn(list);
//
//        this.mvc.perform(
//                get("/").accept(MediaType.APPLICATION_JSON_UTF8)
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                content().string(mapper.writeValueAsString(list))
//        );
//    }
//
//    @Test
//    public void whenLoadThenApplyLoan() throws Exception {
//        List<Loan> list = Collections.singletonList(
//                new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"))
//        );
//        ObjectMapper mapper = new ObjectMapper();
//        given(this.loanService.getByPerson(0)).willReturn(list);
//        this.mvc.perform(
//                get("/0").accept(MediaType.APPLICATION_JSON_UTF8)
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                content().string(mapper.writeValueAsString(list))
//        );
//    }
//
//    @Test
//    public void whenApplyThenSave() throws Exception {
//        Loan loan = new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"));
//        ObjectMapper mapper = new ObjectMapper();
//
//        this.blackListService.isBlackListClient(0);
//        given(this.loanService.apply(loan)).willReturn(loan);
//        this.mvc.perform(
//                post("/").contentType(MediaType.APPLICATION_JSON_UTF8).content(
//                        mapper.writeValueAsString(
//                                loan
//                        )
//                )
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                content().string(mapper.writeValueAsString(new Success<Loan>(loan)))
//        );
//    }
//
//    @Test
//    public void whenInBlacklistThenError() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        given(this.blackListService.isBlackListPerson(0)).willReturn(true);
//        this.mvc.perform(
//                post("/").contentType(MediaType.APPLICATION_JSON_UTF8).content(
//                        mapper.writeValueAsString(
//                                new Loan(new BigDecimal(1000), 60, new Country("Latvia"), new Client("Edgars", "Naglis"))
//                        )
//                )
//        ).andExpect(
//                status().isOk()
//        ).andExpect(
//                content().string(mapper.writeValueAsString(new Error("User 0 in blacklist")))
//        );
//    }
//}
