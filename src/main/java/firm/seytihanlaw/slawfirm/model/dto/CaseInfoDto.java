package firm.seytihanlaw.slawfirm.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import firm.seytihanlaw.slawfirm.model.Client;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaseInfoDto {

    private UUID caseId;

    private boolean caseOpened = false;

    private String judgeName;
    private String hearingDate;
    private String courtName;

}
