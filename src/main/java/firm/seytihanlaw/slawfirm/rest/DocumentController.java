package firm.seytihanlaw.slawfirm.rest;

import firm.seytihanlaw.slawfirm.model.dto.DocumentDto;
import firm.seytihanlaw.slawfirm.services.DocumentService;
import firm.seytihanlaw.slawfirm.util.GenericFileManager;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final GenericFileManager fileManager;
    private final DocumentService documentService;

    public DocumentController(GenericFileManager fileLoader, DocumentService documentService) {
        this.fileManager = fileLoader;
        this.documentService = documentService;
    }


    @GetMapping
    public ResponseEntity<Set<DocumentDto>> getDocuments() throws IOException {

        return ResponseEntity.ok(documentService.getDocuments());

    }

    @GetMapping(value = "/{document_id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable("document_id") UUID document_id) throws IOException {

        return new ResponseEntity<>(documentService.getDocument(document_id), HttpStatus.OK);

    }

    @PostMapping(value = "/{document_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public @ResponseBody byte[] handleGenerate(@PathVariable("document_id") UUID document_id,
                                               @RequestBody DocumentDto documentRequestModel) throws IOException {

        return IOUtils.toByteArray(
                new FileInputStream(documentService.handleMotionGenerics(documentRequestModel.getGenerics(), document_id)));

    }


    @GetMapping(value = "/fdf")
    public @ResponseBody byte[] handleFillable() throws IOException, ParserConfigurationException, SAXException, TransformerException {



//        PDXFAResource xfaRes = fillable.getDocumentCatalog().getAcroForm().getXFA();
//        Document xml = xfaRes.getDocument();
//        NodeList nl = xml.getElementsByTagName("Line11_EMail");
//        for (int i = 0; i < nl.getLength() ; i++ ) {
//            nl.item(i).setTextContent("SAFA SAFA SAFA SAFA");
//        }




//        // Write filled PDF to XML file
//        File f = new File("src/main/resources/static/GenericG28.xfa");
//        FileOutputStream fosXML = new FileOutputStream(f);
//        writeXml(xml, fosXML);

        File genericG28 = fileManager.getG28().getFile();

        PDDocument fillable = PDDocument.load(genericG28);
        fillable.setAllSecurityToBeRemoved(true);
        fillable.getDocumentCatalog().getAcroForm().setNeedAppearances(false);
        List<PDField> fields = fillable.getDocumentCatalog().getAcroForm().getFields();
        for(PDField f: fields ) {
            f.setValue("SAFA KARSLI KARSLI");
        }

        FileOutputStream fos = new FileOutputStream("src/main/resources/static/Generic_G28.pdf");
        fillable.save(fos);


        // Output the filled PDF as byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        fos.write(baos.toByteArray());

        // Output filled PDF to the resources package.
//        COSStream cosout = new COSStream();
//        StreamResult result =   new StreamResult(cosout.createUnfilteredStream());
//        TransformerFactory.newInstance().newTransformer()
//                .transform(new DOMSource(xml),result);
//        result.getOutputStream().close();
//        fillable.getDocumentCatalog().getAcroForm().setXFA(new PDXFAResource(cosout));
//        FileOutputStream fos = new FileOutputStream("src/main/resources/static/Generic_G28.pdf");

        try {
            fillable.save(baos);
            fillable.close();
        } finally {
            baos.close();
        }

        return baos.toByteArray();


    }


    private static void writeXml(org.w3c.dom.Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }


}


