package firm.seytihanlaw.slawfirm.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ClientCreateRequestModel {

    private String fullName;

    private String alienNumber;

    private Integer payment;

    private Integer balance;

    private ATTORNEY responsible;

    private String phoneNumber;

    private String fileDate;

    private String email;

}
