package firm.seytihanlaw.slawfirm.repo;

import firm.seytihanlaw.slawfirm.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByFullNameStartingWithIgnoreCase(String prefix);

    Optional<Client> findBySerialId(Long id);
}
