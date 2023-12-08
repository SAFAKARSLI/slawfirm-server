package firm.seytihanlaw.slawfirm.services;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import firm.seytihanlaw.slawfirm.manager.GoogleCloudManager;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.repo.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class GoogleCloudServiceImpl implements GoogleCloudService{

    private final GoogleCloudManager cloudManager;

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    public GoogleCloudServiceImpl(GoogleCloudManager cloudManager, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.cloudManager = cloudManager;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String createDocument(java.io.File generatedDocument, String fileName) throws IOException {

        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        File uploadedFile = cloudManager.getGDriveService().files().create(fileMetadata,
                new FileContent("application/vnd.openxmlformats-officedocument.wordprocessingml.document", generatedDocument)).setFields("id, webViewLink").execute();

        updatePermission(uploadedFile.getId(), "anyone", "writer"); // :TODO: later set this to be the staff of the office.
        log.info(uploadedFile.getWebViewLink());

        return uploadedFile.getWebViewLink();
    }




    @Override
    public void updatePermission(String fileId, String type, String role) throws IOException {

        JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
            @Override
            public void onFailure(GoogleJsonError e,
                                  HttpHeaders responseHeaders)
                    throws IOException {
                // Handle error
                System.err.println(e.getMessage());
            }

            @Override
            public void onSuccess(Permission permission,
                                  HttpHeaders responseHeaders)
                    throws IOException {
                System.out.println("Permission ID: " + permission.getId());
            }
        };

        BatchRequest batch = cloudManager.getGDriveService().batch();
        Permission permission = new Permission()
                .setType(type)
                .setRole(role);

        cloudManager.getGDriveService().permissions()
                .create(fileId, permission)
                .setFields("id")
                .queue(batch, callback);

        batch.execute();
    }

    @Override
    public BatchGetValuesResponse readDefensiveClients() throws IOException {

        FileList files = cloudManager.getGDriveService().files().list()
                .setQ("sharedWithMe=true")
                .setSupportsTeamDrives(true)
                .setIncludeTeamDriveItems(true)
                .setFields("files(name, id)")
                .setCorpora("allDrives")
                .execute();

        log.info(files.getFiles().get(0).getName());

        log.info(String.valueOf(files.getFiles().size()));

        File file = files.getFiles().stream().filter(e -> Objects.equals(e.getName(), "Mock List")).findFirst().get(); // Mock List

//        log.info(cloudManager.getGSheetsService().spreadsheets().values().get(file.getId(), "Sheet1!B2:B").execute().toString());
        return cloudManager.getGSheetsService().spreadsheets().values().batchGet(file.getId()).setRanges(List.of("Sheet1!B2:B", "Sheet1!C2:C")).execute();

    }

    @Override
    public void checkForUpdate() throws IOException {

        BatchGetValuesResponse excelClients = readDefensiveClients();
        List<List<Object>> nameRange = excelClients.getValueRanges().get(0).getValues();
        List<List<Object>> aNumRange = excelClients.getValueRanges().get(1).getValues();

        for (int i = 0; i < excelClients.getValueRanges().get(0).getValues().size(); i++) {
            Optional<Client> client = clientRepository.findBySerialId(Long.valueOf(i));
            if (client.isPresent()) {
                if (!Objects.equals(client.get().getFullName(), nameRange.get(i).get(0).toString())) {
                    log.info(client.get().getFullName() + " -> " + nameRange.get(i).get(0).toString());
                    client.get().setFullName(nameRange.get(i).get(0).toString());
                }
                if (!aNumRange.get(i).isEmpty() && !Objects.equals(client.get().getAlienNumber(), aNumRange.get(i).get(0).toString()))  {
                    log.info(client.get().getAlienNumber() + " -> " + aNumRange.get(i).get(0).toString());
                    client.get().setAlienNumber(aNumRange.get(i).get(0).toString());
                }
            }

            clientRepository.save(modelMapper.map(client.get(), Client.class));
        }


    }

}