package firm.seytihanlaw.slawfirm.services;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public interface DocumentService {

    File handleGenerics(Map<String, String> generics, UUID fileId);

}
