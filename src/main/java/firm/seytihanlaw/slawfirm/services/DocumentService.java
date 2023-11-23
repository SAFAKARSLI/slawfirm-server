package firm.seytihanlaw.slawfirm.services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public interface DocumentService {

    File handleGenerics(Map<String, String> generics, UUID fileId);

}
