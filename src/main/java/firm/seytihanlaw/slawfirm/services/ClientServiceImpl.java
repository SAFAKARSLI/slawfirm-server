package firm.seytihanlaw.slawfirm.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.model.dto.ClientDto;
import firm.seytihanlaw.slawfirm.repo.CaseRepository;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import firm.seytihanlaw.slawfirm.repo.NtaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final CaseRepository caseRepository;
    private final NtaRepository ntaRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, CaseRepository caseRepository, NtaRepository ntaRepository, ModelMapper mapper) {
        this.clientRepository = clientRepository;
        this.caseRepository = caseRepository;
        this.ntaRepository = ntaRepository;
        this.modelMapper = mapper;

    }

    @Override
    public Set<ClientDto> getClients() {
        log.info(String.valueOf(clientRepository.count()));
        Set<Client> clientSet = new HashSet<>();
        clientRepository.findAll().iterator().forEachRemaining(clientSet::add);
        // Returning the Set of ClientDto for not exposing the entity.
        return clientSet.stream().map(e -> modelMapper.map(e, ClientDto.class)).collect(Collectors.toSet());

    }

    @Override
    public ClientDto findClientById(UUID client_id) {

        Optional<Client> clientFromRepo = clientRepository.findById(client_id);
        log.info(clientFromRepo.toString());
        if (!clientFromRepo.isPresent()) {
            throw new RuntimeException("Client not found with id: " + client_id);
        }

        return modelMapper.map(clientFromRepo.get(), ClientDto.class);
    }

    @Override
    public ClientDto saveClient(ClientDto client) {

        Optional<Client> clientFromRepo = clientRepository.findById(client.getId());
        ClientDto savedClientResponseModel;
        if (clientFromRepo.isPresent()) {
            modelMapper.map(client, clientFromRepo);
            return modelMapper.map(clientRepository.save(clientFromRepo.get()), ClientDto.class);

        } else {
            Client newClient = new Client();
            modelMapper.map(client, newClient);
            newClient = clientRepository.save(newClient);

            savedClientResponseModel = ClientDto.builder()
                    .id(newClient.getId())
                    .fullName(newClient.getFullName())
                    .alienNumber(newClient.getAlienNumber())
                    .build();
        }

        return savedClientResponseModel;

    }

    @Override
    public void findAndUpdateClient(UUID client_id, ClientDto updateClientInfo) {
        ClientDto clientFromRepo = findClientById(client_id);
        log.info(String.valueOf(clientFromRepo.getId()));
        modelMapper.map(updateClientInfo, clientFromRepo);
        log.info(clientFromRepo.toString());
        saveClient(clientFromRepo);
    }

    @Override
    public void updateNtaInfo(UUID client_id, UUID nta_id) {


    }

    @Override
    public void updateCaseInfo(UUID client_id, UUID case_id) {

    }
}
