package firm.seytihanlaw.slawfirm.services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.util.Map;

public interface DocumentService {

    File handleGenerics(Map<String, String> generics, XWPFDocument docx);

}
