package firm.seytihanlaw.slawfirm.rest;

import firm.seytihanlaw.slawfirm.model.dto.ClientDto;
import firm.seytihanlaw.slawfirm.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("clients")
@CrossOrigin(origins = "http://localhost:8080/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDto>> getClients() throws IOException {
        return ResponseEntity.ok(clientService.getClients());
    }



    @GetMapping(value="/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> getClient(@PathVariable("clientId") UUID clientId) {
        return new ResponseEntity<>(clientService.findClientById(clientId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto client) {
        ClientDto client_return = clientService.saveClient(client);
        return new ResponseEntity<>(client_return, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{clientId}")
    public HttpStatus deleteClient(@PathVariable UUID clientId) {
        clientService.deleteClient(clientId);
        return HttpStatus.NO_CONTENT;
    }
}
