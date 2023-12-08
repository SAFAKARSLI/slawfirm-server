package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.model.dto.ClientDto;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<ClientDto> getClients(int pageNum, int pageSize) {
        List<Client> clientList = new ArrayList<>();

//        Pageable pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("serialId"));

        clientRepository.findAll(Sort.by("serialId")).iterator().forEachRemaining(clientList::add);
        // Returning the list of ClientDto for not exposing the entity.
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
    public List<ClientDto> findClientsByName(String name) {
        List<Client> matchedClients = clientRepository.findByFullNameStartingWithIgnoreCase(name);
        return matchedClients.stream().map(e -> modelMapper.map(e, ClientDto.class)).collect(Collectors.toList());
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
    public void saveClients(List<ClientDto> clients) {
        for (ClientDto client : clients)  {
            saveClient(client);
        }
    }

    @Override
    public void deleteClient(UUID clientId) {

        clientRepository.deleteById(clientId);
    }


}
