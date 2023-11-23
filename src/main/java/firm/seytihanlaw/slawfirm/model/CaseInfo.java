package firm.seytihanlaw.slawfirm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
public class CaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID caseId;

    private boolean caseOpened = false;

    private String judgeName;
    private String hearingDate;
    private String courtName;

    @OneToOne(mappedBy = "caseInfo", cascade=CascadeType.ALL)
    private Client client;

}
