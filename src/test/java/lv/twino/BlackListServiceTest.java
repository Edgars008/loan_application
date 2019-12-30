package lv.twino;

import lv.twino.model.BlackList;
import lv.twino.model.Client;
import lv.twino.repository.BlackListRepository;
import lv.twino.repository.ClientRepository;
import lv.twino.service.BlackListService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlackListServiceTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private BlackListService blackListService;

    @Test
    public void whenClientIsInTheBlackListThenReturnTrue() {
        Client client = this.clientRepository.save(new Client("Edgars", "Naglis"));
        this.blackListRepository.save(new BlackList(client));
        boolean result = this.blackListService.isBlackListClient(client.getId());
        assertTrue(result);
    }

    @Test
    public void whenBlackListEmptyThenAnyPersonNotIn() {
        Client client = this.clientRepository.save(new Client("Edgars", "Naglis"));
        boolean result = this.blackListService.isBlackListClient(client.getId());
        assertFalse(result);
    }
}
