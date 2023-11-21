package firm.seytihanlaw.slawfirm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NTAInfo {

    private String locationOfEntry;

    private String dateOfEntry;

    private String ntaDate;

}
