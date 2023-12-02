package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.model.dto.DocumentDto;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface DocumentService {

    File handleMotionGenerics(Map<String, String> generics, UUID fileId) throws IOException;

    Set<DocumentDto> getDocuments();


    DocumentDto getDocument(UUID documentId);
}
