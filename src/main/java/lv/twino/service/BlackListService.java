package lv.twino.service;

import lv.twino.model.Client;
import lv.twino.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlackListService {

    @Autowired
    private BlackListRepository repository;

      public boolean isBlackListClient(Integer clientId) {
          return repository.findByClient(new Client(clientId)) != null;

    }


}
