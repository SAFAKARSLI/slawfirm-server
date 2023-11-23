package firm.seytihanlaw.slawfirm.bootstrap;

import firm.seytihanlaw.slawfirm.model.Document;
import firm.seytihanlaw.slawfirm.repo.DocumentRepository;
import firm.seytihanlaw.slawfirm.util.GenericFileManager;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.UUID;

@Component
public class GenericDocumentBootstrapLoader implements CommandLineRunner {

    private final GenericFileManager fileManager;
    private final DocumentRepository documentRepository;


    public GenericDocumentBootstrapLoader(GenericFileManager fileManager, DocumentRepository documentRepository) {
        this.fileManager = fileManager;
        this.documentRepository = documentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Document genericWrittenPlea = Document.builder()
                .id(UUID.fromString("233f7654-89b3-11ee-b9d1-0242ac120002"))
                .fileName(fileManager.getPlea().getFile().getName())
                .fileContent(IOUtils.toByteArray(new FileInputStream(fileManager.getPlea().getFile()))).build();
        documentRepository.save(genericWrittenPlea);

    }
}
