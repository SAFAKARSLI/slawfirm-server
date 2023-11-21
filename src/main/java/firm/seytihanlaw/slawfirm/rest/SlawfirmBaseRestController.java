package firm.seytihanlaw.slawfirm.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.model.ClientCreateRequestModel;
import firm.seytihanlaw.slawfirm.model.ClientCreateResponseModel;
import firm.seytihanlaw.slawfirm.model.ClientUpdateRequestModel;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import firm.seytihanlaw.slawfirm.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("clients")
public class SlawfirmBaseRestController {

    private final ClientService clientService;

    public SlawfirmBaseRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Client>> getClients() throws IOException {
        return ResponseEntity.ok(clientService.getClients());
    }



    @GetMapping(value="/{client_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Client>> getClient(@PathVariable("client_id") Long client_id) {
        return new ResponseEntity<>(clientService.findClientById(client_id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ClientCreateResponseModel> createClient(@RequestBody ClientCreateRequestModel client) {


        ModelMapper mapper = new ModelMapper();
        Client new_client = mapper.map(client, Client.class);

        log.info(new_client.toString());

        ClientCreateResponseModel client_return = clientService.createClient(new_client);
        return new ResponseEntity<>(client_return, HttpStatus.CREATED);
    }


    @PutMapping(value="/{client_id}")
    public ResponseEntity updateClient(@RequestBody ClientUpdateRequestModel client, @PathVariable("client_id") Long client_id) {
        clientService.findAndUpdateClient(client_id, client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
