package lv.twino.service;

import lv.twino.model.BlackList;
import lv.twino.model.Client;
import lv.twino.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlackListService {

    @Autowired
    private BlackListRepository repository;

      public boolean isBlackListClient(Integer clientId) {
          return this.repository.findByClient(new Client(clientId)) != null;

    }

    public BlackList saveInBlackList(final BlackList blackList){
        return this.repository.save(blackList);
    }


}
