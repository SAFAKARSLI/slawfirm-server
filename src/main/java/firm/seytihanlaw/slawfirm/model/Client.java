package firm.seytihanlaw.slawfirm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String alienNumber;

    private Integer payment;

    private Integer balance;

    private ATTORNEY responsible;

    private String phoneNumber;

    private String fileDate;

    private String email;

//    private Case caseInfo = new Case();
//    private NTAInfo nta;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", alienNumber='" + alienNumber + '\'' +
                ", payment=" + payment +
                ", balance=" + balance +
                ", responsible=" + responsible +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fileDate='" + fileDate + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
