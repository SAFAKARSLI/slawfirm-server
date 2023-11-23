package firm.seytihanlaw.slawfirm.model.request;

import firm.seytihanlaw.slawfirm.types.ATTORNEY;
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
