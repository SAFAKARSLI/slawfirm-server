package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.model.dto.ClientDto;

import java.util.Set;
import java.util.UUID;


public interface ClientService {

    Set<ClientDto> getClients();
    ClientDto findClientById(UUID client_id);
    ClientDto saveClient(ClientDto client);
    void findAndUpdateClient(UUID client_id, ClientDto updateClientInfo);
    void updateNtaInfo(UUID client_id, UUID nta_id);
    void updateCaseInfo(UUID client_id, UUID case_id);

}
