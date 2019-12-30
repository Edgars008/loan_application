package lv.twino.repository;

import lv.twino.model.Client;
import lv.twino.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {

    List<Loan> findByPerson(Client client);

    Loan apply(Loan loan);

    List<Loan> getAll();

    List<Loan> getByClient(int clientId);

}
