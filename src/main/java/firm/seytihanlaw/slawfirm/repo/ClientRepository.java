package firm.seytihanlaw.slawfirm.repo;

import firm.seytihanlaw.slawfirm.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
