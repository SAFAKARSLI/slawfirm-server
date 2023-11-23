package firm.seytihanlaw.slawfirm.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
@Setter
@Component
@Slf4j
public class GenericFileManager {

    @Value("classpath:static/Generic Written Plea.docx")
    private Resource plea;

    @Value("classpath:static/Generic Retainer Agreement.docx")
    private Resource agreement;

    @Value("classpath:static/Generic Motion to Change Venue.docx")
    private Resource cov;
    

}
