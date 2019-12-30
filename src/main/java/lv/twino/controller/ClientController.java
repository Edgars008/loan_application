package lv.twino.controller;

import lv.twino.model.Client;
import lv.twino.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;
 
    @PostMapping("/clients")
    public ResponseEntity<Void> createClients() {
        clientService.createClient();
      
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/clients/{clientId}")
    public Client retrieveClient(@PathVariable Integer clientId) {
        return clientService.retrieveClient(clientId);
       
    }

}
