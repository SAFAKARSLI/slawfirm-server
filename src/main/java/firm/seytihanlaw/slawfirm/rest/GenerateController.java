package firm.seytihanlaw.slawfirm.rest;

import firm.seytihanlaw.slawfirm.model.GenerateDocumentRequestModel;
import firm.seytihanlaw.slawfirm.services.DocumentService;
import firm.seytihanlaw.slawfirm.util.GenericFileManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@Slf4j
@RestController
@RequestMapping("clients/{client_id}/generate")
public class GenerateController {

    private final GenericFileManager fileLoader;
    private final DocumentService documentService;

    public GenerateController(GenericFileManager fileLoader, DocumentService documentService) {
        this.fileLoader = fileLoader;
        this.documentService = documentService;
    }

    @RequestMapping(value = "/written-plea", method = RequestMethod.GET, produces="application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public @ResponseBody byte[] generateWrittenPlea(@RequestBody GenerateDocumentRequestModel documentRequestModel) throws IOException {

        File file = fileLoader.getPlea().getFile();
        InputStream fis = new FileInputStream(file);
        XWPFDocument docx = new XWPFDocument(fis);

        log.info(documentRequestModel.getGenerics().get("file_id"));

        return IOUtils.toByteArray(
                new FileInputStream(documentService.handleGenerics(documentRequestModel.getGenerics(), docx))
        );

    }


}
