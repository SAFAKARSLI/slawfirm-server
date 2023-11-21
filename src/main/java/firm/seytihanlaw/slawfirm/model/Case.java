package firm.seytihanlaw.slawfirm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Case {

    private boolean caseOpened = false;

    private Date docketDate;
    private String judgeName;
    private String hearingDate;
    private String hearingType;
    private String courtDate;


    public Case(Date docketDate, String judgeName, String hearingDate, String hearingType, String courtDate) {
        this.docketDate = docketDate;
        this.judgeName = judgeName;
        this.hearingDate = hearingDate;
        this.hearingType = hearingType;
        this.courtDate = courtDate;
        this.caseOpened = true;
    }
}
