package firm.seytihanlaw.slawfirm.rest;

import firm.seytihanlaw.slawfirm.model.dto.DocumentDto;
import firm.seytihanlaw.slawfirm.services.DocumentService;
import firm.seytihanlaw.slawfirm.util.GenericFileManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("clients/{client_id}/generate")
public class GenerateController {

    private final GenericFileManager fileManager;
    private final DocumentService documentService;

    public GenerateController(GenericFileManager fileLoader, DocumentService documentService) {
        this.fileManager = fileLoader;
        this.documentService = documentService;
    }

    @RequestMapping(
            method = RequestMethod.GET, produces="application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public @ResponseBody byte[] handleGenerate(@RequestBody DocumentDto documentRequestModel) throws IOException {

        return IOUtils.toByteArray(
                new FileInputStream(documentService.handleGenerics(documentRequestModel.getGenerics(), UUID.fromString(documentRequestModel.getFile_id())))
        );

    }


}
