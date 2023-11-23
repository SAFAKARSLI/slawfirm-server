package firm.seytihanlaw.slawfirm.util;

import firm.seytihanlaw.slawfirm.model.ATTORNEY;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.Iterator;

@Component
public class DefensiveClientDataLoader implements CommandLineRunner {

    ClientRepository clientRepository;

    @Value("classpath:static/Slawfirm_Defensive_Clients.xlsx")
    Resource resource;

    public DefensiveClientDataLoader(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        FileInputStream file = new FileInputStream(resource.getFile());

        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet ws = wb.getSheetAt(0);
        Iterator<Row> rowIterator = ws.iterator();

        System.out.println(ws.getLastRowNum());
        for(int i = 1; i< ws.getLastRowNum(); i++) {

            Row row = ws.getRow(i);

            System.out.println(row.getCell(0));

            ATTORNEY responsible = switch (row.getCell(4).toString()) {
                case "AHMET" -> ATTORNEY.AHMET_SEYITHANOGLU;
                case "CENAB" -> ATTORNEY.CENAB_YAVUZ;
                case "MELIKSAH" -> ATTORNEY.MELIKSAH_DURMUS;
                case "NURULLAH" -> ATTORNEY.NURULLAH_CELIK;
                case "SINAN" -> ATTORNEY.SINAN_SARI;
                case "TURHAN" -> ATTORNEY.SINAN_TURHAN;
                case "YUSUF" -> ATTORNEY.YUSUF_MENEK;
                default -> null;
            };



            Client client = Client.builder()
                    .fullName(String.valueOf(row.getCell(0)))
                    .alienNumber(String.valueOf(row.getCell(1)))
                    .responsible(responsible)
                    .phoneNumber(String.valueOf(row.getCell(5)))
                    .fileDate(String.valueOf(row.getCell(6)))
                    .email(String.valueOf(row.getCell(7)))
                    .build();

            if (String.valueOf(row.getCell(2)).isEmpty()) {
                client.builder().payment(0);
            } else client.builder().payment(Integer.valueOf(String.valueOf(row.getCell(2)).split("\\.")[0]));

            if (row.getCell(3).toString().isEmpty()) {
                client.builder().balance(0);
            } else client.builder().balance(Integer.valueOf(String.valueOf(row.getCell(3)).split("\\.")[0]));

            clientRepository.save(client);
        }
    }
}
