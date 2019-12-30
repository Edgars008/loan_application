package lv.twino.repository;

import lv.twino.model.BlackList;
import lv.twino.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends CrudRepository<BlackList, Integer> {
    BlackList findByPerson(Client client);
}
