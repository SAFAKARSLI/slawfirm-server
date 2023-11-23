package firm.seytihanlaw.slawfirm.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import firm.seytihanlaw.slawfirm.model.Client;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NtaInfoDto {

    private UUID ntaId;
    private String placeOfEntry;
    private String dateOfEntry;
    private String ntaDate;

}
