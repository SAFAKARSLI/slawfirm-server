package firm.seytihanlaw.slawfirm.rest;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import firm.seytihanlaw.slawfirm.services.ClientService;
import firm.seytihanlaw.slawfirm.services.GoogleCloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("cloud")
public class GoogleCloudController {

    private final GoogleCloudService cloudService;

    private final ClientService clientService;

    public GoogleCloudController(GoogleCloudService cloudService, ClientService clientService) {
        this.cloudService = cloudService;
        this.clientService = clientService;
    }

//    @GetMapping(value = "checkForUpdates")
//    @Scheduled(fixedRate = 10000)
//    public void checkForUpdates() throws IOException {
//
//        cloudService.checkForUpdate();
//
//    }


}
