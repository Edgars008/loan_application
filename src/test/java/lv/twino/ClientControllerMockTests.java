package lv.twino;

import lv.twino.model.Client;
import lv.twino.repository.ClientRepository;
import lv.twino.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerMockTests {

    @Autowired
    private ClientService clientService;
    
    @MockBean
    private ClientRepository clientRepository;
 
    @Test
    public void testRetrieveClientWithMockRepository() throws Exception {
  
        Optional<Client> optClient = Optional.of( new Client("Rajesh","Bhojwani"));
        when(clientRepository.findById(1)).thenReturn(optClient);

        assertTrue(clientService.retrieveClient(1).getName().contains("Rajesh"));
    }
 
}

