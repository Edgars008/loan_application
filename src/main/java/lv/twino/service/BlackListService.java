package lv.twino.service;

import lv.twino.model.Client;
import lv.twino.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {

    @Autowired
    private BlackListRepository repository;

      public boolean isBlackListClient(Integer clientId) {
          return repository.findByPerson(new Client(clientId)) != null;

    }


}
