package firm.seytihanlaw.slawfirm.rest;

import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("clients")
public class SlawfirmBaseRestController {

    ClientRepository clientRepository;

    public SlawfirmBaseRestController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RequestMapping()
    public String getClients() throws IOException {

        StringBuilder output = new StringBuilder();

        int i;
        for (i = 0; i < clientRepository.count(); i++) {
            output.append(clientRepository.findById((long) i));
        }

        return output.toString();
    }


}
