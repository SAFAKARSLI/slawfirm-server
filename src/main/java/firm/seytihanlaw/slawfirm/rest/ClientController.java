package firm.seytihanlaw.slawfirm.rest;

import firm.seytihanlaw.slawfirm.model.dto.ClientDto;
import firm.seytihanlaw.slawfirm.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ClientDto>> getClients() throws IOException {
        return ResponseEntity.ok(clientService.getClients());
    }



    @GetMapping(value="/{client_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> getClient(@PathVariable("client_id") UUID client_id) {
        return new ResponseEntity<>(clientService.findClientById(client_id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto client) {
        client.setId(UUID.randomUUID());
        ClientDto client_return = clientService.saveClient(client);
        return new ResponseEntity<>(client_return, HttpStatus.CREATED);
    }


    @PutMapping(value="/{client_id}")
    public ResponseEntity updateClient(@RequestBody ClientDto updateClient, @PathVariable("client_id") UUID client_id) {
        clientService.findAndUpdateClient(client_id, updateClient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
