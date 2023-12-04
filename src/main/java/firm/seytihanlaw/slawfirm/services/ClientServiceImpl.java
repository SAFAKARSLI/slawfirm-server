package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.model.dto.ClientDto;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper mapper) {
        this.clientRepository = clientRepository;

        this.modelMapper = mapper;

    }

    @Override
    public List<ClientDto> getClients() {
        log.info(String.valueOf(clientRepository.count()));
        List<Client> clientList = new ArrayList<>();
        clientRepository.findAll(Sort.by("serialId")).iterator().forEachRemaining(clientList::add);
        // Returning the Set of ClientDto for not exposing the entity.
        return clientList.stream().map(e -> modelMapper.map(e, ClientDto.class)).collect(Collectors.toList());
    }

    @Override
    public ClientDto findClientById(UUID client_id) {

        Optional<Client> clientFromRepo = clientRepository.findById(client_id);
        if (!clientFromRepo.isPresent()) {
            throw new RuntimeException("Client not found with id: " + client_id);
        }

        return modelMapper.map(clientFromRepo.get(), ClientDto.class);
    }

    @Override
    public ClientDto saveClient(ClientDto client) {
        client.setSerialId(clientRepository.count());
        Client newClient = new Client();
        modelMapper.map(client, newClient);
        newClient = clientRepository.save(newClient);
        return modelMapper.map(newClient, ClientDto.class);
    }

    @Override
    public void deleteClient(UUID clientId) {

        clientRepository.deleteById(clientId);
    }
}
