package firm.seytihanlaw.slawfirm.model.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCreateResponseModel {

    private Long id;

    private String fullName;

    private String alienNumber;
}
