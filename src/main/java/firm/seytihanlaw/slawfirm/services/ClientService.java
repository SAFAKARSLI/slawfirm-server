package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.model.dto.ClientDto;

import java.util.List;
import java.util.UUID;


public interface ClientService {

    List<ClientDto> getClients(int pageNum, int pageSize);
    ClientDto findClientById(UUID client_id);
    ClientDto saveClient(ClientDto client);
    void saveClients(List<ClientDto> clients);
    void deleteClient(UUID clientId);

    List<ClientDto> findClientsByName(String name);


}
