package firm.seytihanlaw.slawfirm.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DocumentServiceImpl implements DocumentService {



    @Override
    public File handleGenerics(Map<String, String> generics, XWPFDocument docx) {

        Iterator<XWPFParagraph> paragraph_itr = docx.getParagraphsIterator();


        XWPFParagraph p;
        while(paragraph_itr.hasNext()) {
            p = paragraph_itr.next();
            if (isAnyReplaceable(generics.keySet().stream().toList(), p)) {

                // Replace "generics" (e.g, ${COURT_NAME}) with "replaceTexts"
                for (String generic : generics.keySet()) {

                    String formatted_generic = "${"+generic.toUpperCase()+"}"; // TODO HANDLE LATER (CUSTOMIZABLE SYNTAX)!

                    if (p.getText().contains(formatted_generic)) {
                        String replaceText = generics.get(generic);
                        log.info(replaceText);
                        TextSegment segment = p.searchText(formatted_generic, new PositionInParagraph());
                        List<XWPFRun> segmentRuns = new ArrayList<>();
                        for (int i = segment.getBeginRun(); i <= segment.getEndRun(); i++) {
                            segmentRuns.add(p.getRuns().get(i));
                        }

                        String multipleRunText = segmentRuns.stream().map(XWPFRun::text).collect(Collectors.joining());
                        XWPFRun r = p.insertNewRun(segment.getEndRun() + 1);

                        r.setBold(segmentRuns.get(0).isBold());
                        r.setCapitalized(segmentRuns.get(0).isCapitalized());
                        r.setFontFamily(segmentRuns.get(0).getFontFamily());
                        r.setText(multipleRunText.replace(formatted_generic, replaceText), 0);

                        for (int i = 0; i < segmentRuns.size(); i++) {
                            p.removeRun(segment.getBeginRun());
                        }
                    }
                }

            }
        }
        try {
            log.info(docx.getParagraphs().get(12).getText());
            File outputFile = new File("src/main/resources/output/"+ LocalDate.now()+".docx");
            docx.write(new FileOutputStream(outputFile));
            return outputFile;

        } catch (IOException e) {
            throw new RuntimeException("An error occurred during the generation of the document.");
        }


    }

    private boolean isAnyReplaceable(List<String> generics, XWPFParagraph p) {
        return generics.stream()
                .map(e -> "${"+e.toUpperCase()+"}")
                .anyMatch(e -> p.getText().contains(e));
    }
}
