package firm.seytihanlaw.slawfirm.bootstrap;

import firm.seytihanlaw.slawfirm.model.Document;
import firm.seytihanlaw.slawfirm.repo.DocumentRepository;
import firm.seytihanlaw.slawfirm.manager.FileManager;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

@Component
public class GenericDocumentBootstrapLoader implements CommandLineRunner {

    private final FileManager fileManager;
    private final DocumentRepository documentRepository;


    public GenericDocumentBootstrapLoader(FileManager fileManager, DocumentRepository documentRepository) {
        this.fileManager = fileManager;
        this.documentRepository = documentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        File retainer = fileManager.getAgreement().getFile();
        Document genericRetainerAgreement = Document.builder()
                .id(UUID.fromString("62ae3b52-8ff0-11ee-b9d1-0242ac120002"))
                .name(fileManager.getAgreement().getFile().getName())
                .content(IOUtils.toByteArray(new FileInputStream(fileManager.getAgreement().getFile()))).build();
        documentRepository.save(genericRetainerAgreement);

        File plea = fileManager.getPlea().getFile();
        Document genericWrittenPlea = Document.builder()
                .id(UUID.fromString("233f7654-89b3-11ee-b9d1-0242ac120002"))
                .name(plea.getName())
                .content(IOUtils.toByteArray(new FileInputStream(plea))).build();
        documentRepository.save(genericWrittenPlea);




    }





}
