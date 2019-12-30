package lv.twino.service;

import lv.twino.model.Client;
import lv.twino.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> createClient() {
        List<Client> clients = new ArrayList<Client>();
        List<Client> savedClients = new ArrayList<Client>();
        
        clients.add(new Client("Rajesh", "Bhojwani"));
        clients.add(new Client("Sumit", "Sharma"));
        clients.add(new Client("Rohit", "Chauhan"));
        Iterable<Client> itrClients=repository.saveAll(clients);
        itrClients.forEach(savedClients::add);

        return savedClients;
    }

    public Client retrieveClient(Integer clientId) {
       return repository.findById(clientId).orElse(new Client());
  
    }
 
}
