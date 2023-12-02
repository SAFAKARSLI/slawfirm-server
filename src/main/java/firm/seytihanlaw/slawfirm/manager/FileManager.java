package firm.seytihanlaw.slawfirm.manager;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class FileManager {

    @Value("classpath:static/Generic Written Plea.docx")
    private Resource plea;

    @Value("classpath:static/Generic Retainer Agreement.docx")
    private Resource agreement;

    @Value("classpath:static/Generic Motion to Change Venue.docx")
    private Resource cov;

    @Value("classpath:static/Generic_G28_Document.pdf")
    private Resource g28;
    

}
