package firm.seytihanlaw.slawfirm.services;

import firm.seytihanlaw.slawfirm.model.Document;
import firm.seytihanlaw.slawfirm.model.dto.DocumentDto;
import firm.seytihanlaw.slawfirm.repo.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public File handleMotionGenerics(Map<String, String> generics, UUID fileId) throws IOException {

        Optional<Document> doc = documentRepository.findById(fileId);

        if (doc.isPresent()) {
            InputStream fis = new ByteArrayInputStream(doc.get().getContent());
            XWPFDocument docx = new XWPFDocument(fis);

            Iterator<XWPFParagraph> paragraph_itr = docx.getParagraphsIterator();

            XWPFParagraph p;
            while(paragraph_itr.hasNext()) {
                p = paragraph_itr.next();
                if (isAnyReplaceable(generics.keySet().stream().toList(), p)) {

                    // Replace "generics" (e.g, {COURT_NAME}) with "replaceTexts"
                    for (String generic : generics.keySet()) {

                        String formatted_generic = "{"+generic.toUpperCase()+"}";

                        if (p.getText().contains(formatted_generic)) {
                            String replaceText = generics.get(generic);

                            TextSegment segment = p.searchText(formatted_generic, new PositionInParagraph());
                            List<XWPFRun> segmentRuns = new ArrayList<>();
                            for (int i = segment.getBeginRun(); i <= segment.getEndRun(); i++) {
                                segmentRuns.add(p.getRuns().get(i));
                            }

                            XWPFRun r = p.insertNewRun(segment.getEndRun() + 1);

                            if (replaceText.contains("\n")) {
                                String[] lines = replaceText.split("\n");
                                for (String line : lines) {
                                    r.setText(line);
                                    r.addBreak();
                                    r = p.insertNewRun(p.getRuns().indexOf(r) + 1);
                                };
                            } else {
                                String multipleRunText = segmentRuns.stream().map(XWPFRun::text).collect(Collectors.joining());
                                r.setText(multipleRunText.replace(formatted_generic, replaceText), 0);
                            }

                            r.setBold(segmentRuns.get(0).isBold());
                            r.setFontSize(segmentRuns.get(0).getFontSizeAsDouble());
                            r.setCapitalized(segmentRuns.get(0).isCapitalized());
                            r.setFontFamily(segmentRuns.get(0).getFontFamily());

                            for (int i = 0; i < segmentRuns.size(); i++) {
                                p.removeRun(segment.getBeginRun());
                            }
                        }
                    }
                }
            }
            try {
                File outputFile = new File("src/main/resources/output/"+ LocalDate.now()+".docx");
                outputFile.setWritable(true);
                docx.write(new FileOutputStream(outputFile));
                return outputFile;

            } catch (IOException e) {
                throw new RuntimeException("An error occurred during the generation of the document.");
            }

        } else {
            throw new RuntimeException("Specified document (UUID: "+ fileId +") could not be found.");
        }





    }

    @Override
    public Set<DocumentDto> getDocuments() {
        Set<Document> documentsSet = new HashSet<>();
        documentRepository.findAll().iterator().forEachRemaining(documentsSet::add);
        return documentsSet.stream().map(e -> modelMapper.map(e, DocumentDto.class)).collect(Collectors.toSet());
    }

    @Override
    public DocumentDto getDocument(UUID documentId) {
        Optional<Document> docFromRepo = documentRepository.findById(documentId);
        if (!docFromRepo.isPresent()) {
            throw new RuntimeException("Document not found with id: " + documentId);
        }

        return modelMapper.map(docFromRepo.get(), DocumentDto.class);
    }

    private boolean isAnyReplaceable(List<String> generics, XWPFParagraph p) {
        return generics.stream()
                .map(e -> "{"+e.toUpperCase()+"}")
                .anyMatch(e -> p.getText().contains(e));
    }
}
