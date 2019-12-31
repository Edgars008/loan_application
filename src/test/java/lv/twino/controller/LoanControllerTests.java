package lv.twino.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    
    HttpHeaders headers = new HttpHeaders();
    
// This is required only if testRetrieveStudent is tested without testCreateStudent() which creates student records.   
//    @Before
//    public void setup() {
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//        restTemplate.exchange(createURLWithPort("/students"), HttpMethod.POST, entity, String.class);
//
//    }
    @Test
    public void testCreateLoan() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/loans"), HttpMethod.POST, entity, String.class);
       
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        
        assertTrue(actual.contains("/loans"));
    }    

    @Test
    public void testRetrieveLoan() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/loans/1"), HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":1,\"name\":\"Rajesh\",\"surname\":\"Bhojwani\"}";
        
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
 
}

