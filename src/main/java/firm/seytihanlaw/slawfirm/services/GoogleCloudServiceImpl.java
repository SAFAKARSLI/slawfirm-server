package firm.seytihanlaw.slawfirm.services;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import firm.seytihanlaw.slawfirm.manager.GoogleCloudManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class GoogleCloudServiceImpl implements GoogleCloudService{

    private final GoogleCloudManager cloudManager;

    public GoogleCloudServiceImpl(GoogleCloudManager cloudManager) {
        this.cloudManager = cloudManager;
    }

    @Override
    public String createDocument(java.io.File generatedDocument, String fileName) throws IOException {

        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        File uploadedFile = cloudManager.getGDriveService().files().create(fileMetadata,
                new FileContent("application/vnd.openxmlformats-officedocument.wordprocessingml.document", generatedDocument)).execute();

        updatePermission(uploadedFile.getId(), "anyone", "writer"); // :TODO: later set this to be the staff of the office.

        return "https://docs.google.com/document/d/" + uploadedFile.getId();
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
}