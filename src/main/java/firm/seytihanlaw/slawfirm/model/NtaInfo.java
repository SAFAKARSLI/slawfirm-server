package firm.seytihanlaw.slawfirm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NtaInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID ntaId;

    private String placeOfEntry;

    private String dateOfEntry;

    private String ntaDate;

    @OneToOne (mappedBy = "ntaInfo", targetEntity = Client.class, cascade=CascadeType.ALL)
    private Client client;

}
