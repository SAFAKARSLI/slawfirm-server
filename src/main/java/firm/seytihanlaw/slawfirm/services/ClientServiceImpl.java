package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.mapper.ClientMapper;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.model.response.ClientCreateResponseModel;
import firm.seytihanlaw.slawfirm.model.request.ClientUpdateRequestModel;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Set<Client> getClients() {
        log.info(String.valueOf(clientRepository.count()));
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findAll().iterator().forEachRemaining(clientSet::add);
        return clientSet;
    }

    @Override
    public Optional<Client> findClientById(Long client_id) {

        Optional<Client> clientFromRepo = clientRepository.findById(client_id);

        if (!clientFromRepo.isPresent()) {
            throw new RuntimeException("Client not found with id: " + client_id);
        }

        return clientFromRepo;
    }

    @Override
    public ClientCreateResponseModel createClient(Client client) {
        clientRepository.save(client);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(client, ClientCreateResponseModel.class);
    }

    @Override
    public void findAndUpdateClient(Long client_id, @RequestBody ClientUpdateRequestModel updatedClientInfo) {
        Optional<Client> clientFromRepo = clientRepository.findById(client_id);

        if (!clientFromRepo.isPresent()) {
            throw new RuntimeException("Client not found with id: " + client_id);
        }

        clientMapper.mapClient(updatedClientInfo, clientFromRepo.get());


        clientRepository.save(clientFromRepo.get());

    }
}
