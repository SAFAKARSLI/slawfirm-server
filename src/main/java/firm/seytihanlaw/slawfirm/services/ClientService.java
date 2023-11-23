package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.model.response.ClientCreateResponseModel;
import firm.seytihanlaw.slawfirm.model.request.ClientUpdateRequestModel;

import java.util.Optional;
import java.util.Set;


public interface ClientService {

    Set<Client> getClients();

    Optional<Client> findClientById(Long client_id);

    ClientCreateResponseModel createClient(Client client);

    void findAndUpdateClient(Long client_id, ClientUpdateRequestModel client);

}
