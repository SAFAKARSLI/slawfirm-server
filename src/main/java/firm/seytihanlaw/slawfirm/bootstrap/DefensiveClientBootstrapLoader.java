package firm.seytihanlaw.slawfirm.bootstrap;

import firm.seytihanlaw.slawfirm.manager.FileManager;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.Iterator;

//@Component
public class DefensiveClientBootstrapLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final FileManager fileManager;

    public DefensiveClientBootstrapLoader(ClientRepository clientRepository, FileManager fileManager) {
        this.clientRepository = clientRepository;
        this.fileManager = fileManager;
    }


    @Override
    public void run(String... args) throws Exception {

        FileInputStream file = new FileInputStream(fileManager.getDefensiveClients().getFile());

        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet ws = wb.getSheetAt(0);

        for(int i = 1; i<= ws.getLastRowNum(); i++) {

            Row row = ws.getRow(i);

            Client client = Client.builder()
                    .serialId(clientRepository.count())
                    .fullName(String.valueOf(row.getCell(0)))
                    .alienNumber(String.valueOf(row.getCell(1)))
                    .build();

            clientRepository.save(client);
        }
    }
}
