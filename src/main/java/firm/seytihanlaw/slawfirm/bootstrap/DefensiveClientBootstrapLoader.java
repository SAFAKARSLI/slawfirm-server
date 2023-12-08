package firm.seytihanlaw.slawfirm.bootstrap;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import firm.seytihanlaw.slawfirm.manager.FileManager;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import firm.seytihanlaw.slawfirm.services.GoogleCloudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
@Slf4j
public class DefensiveClientBootstrapLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final GoogleCloudService cloudService;
    private final FileManager fileManager;

    public DefensiveClientBootstrapLoader(ClientRepository clientRepository, GoogleCloudService cloudService, FileManager fileManager) {
        this.clientRepository = clientRepository;
        this.cloudService = cloudService;
        this.fileManager = fileManager;
    }


    @Override
    public void run(String... args) throws Exception {

        BatchGetValuesResponse clientList = cloudService.readDefensiveClients();
        log.info(clientList.toString());

        log.info(String.valueOf(clientList.getValueRanges().get(0).getValues().size()));
        log.info(String.valueOf(clientList.getValueRanges().get(1).getValues().size()));

        for (int i = 0; i < clientList.getValueRanges().get(0).getValues().size(); i++) {
            Client client = Client.builder().serialId(clientRepository.count()).build();


            client.setFullName(clientList.getValueRanges().get(0).getValues().get(i).get(0).toString());
            if (clientList.getValueRanges().get(1).getValues().get(i).isEmpty()) {
                client.setAlienNumber(null);
            } else {
                client.setAlienNumber(clientList.getValueRanges().get(1).getValues().get(i).get(0).toString());
            }


            log.info(String.valueOf(i));
            clientRepository.save(client);
        }
    }




//        FileInputStream file = new FileInputStream(fileManager.getDefensiveClients().getFile());
//
//        XSSFWorkbook wb = new XSSFWorkbook(file);
//        XSSFSheet ws = wb.getSheetAt(0);
//
//        for(int i = 1; i<= ws.getLastRowNum(); i++) {
//
//            Row row = ws.getRow(i);
//
//            Client client = Client.builder()
//                    .serialId(clientRepository.count())
//                    .fullName(String.valueOf(row.getCell(0)))
//                    .alienNumber(String.valueOf(row.getCell(1)))
//                    .build();
//
//            clientRepository.save(client);
//        }
}

