package firm.seytihanlaw.slawfirm.services;

import com.google.api.services.drive.model.File;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;

import java.io.IOException;
import java.util.Map;

public interface GoogleCloudService {

    String createDocument(java.io.File generatedDocument, String fileName) throws IOException;

    void updatePermission(String fileId, String role, String type) throws IOException;

    BatchGetValuesResponse readDefensiveClients() throws IOException;

    void checkForUpdate() throws IOException;

}
