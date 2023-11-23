package firm.seytihanlaw.slawfirm.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import firm.seytihanlaw.slawfirm.model.CaseInfo;
import firm.seytihanlaw.slawfirm.model.NtaInfo;
import firm.seytihanlaw.slawfirm.types.ATTORNEY;
import firm.seytihanlaw.slawfirm.types.GENDER;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL) // For creating dynamic response models.
public class ClientDto {

    private UUID id;
    private String fullName;
    private GENDER gender;
    private String alienNumber;
    private Integer payment;
    private Integer balance;
    private ATTORNEY responsible;
    private String phoneNumber;
    private String fileDate;
    private String email;

    private NtaInfoDto ntaInfo;
    private CaseInfoDto caseInfo;

}
