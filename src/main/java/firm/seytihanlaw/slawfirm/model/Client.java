package firm.seytihanlaw.slawfirm.model;

import firm.seytihanlaw.slawfirm.types.ATTORNEY;
import firm.seytihanlaw.slawfirm.types.GENDER;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID id;

    private String fullName;

    private String alienNumber;

    private GENDER gender;

    private Integer payment;

    private Integer balance;

    @Enumerated(EnumType.STRING)
    private ATTORNEY responsible;

    private String phoneNumber;

    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nta_id", referencedColumnName = "ntaId")
    private NtaInfo ntaInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", referencedColumnName = "caseId")
    private CaseInfo caseInfo;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", alienNumber='" + alienNumber + '\'' +
                ", gender=" + gender +
                ", payment=" + payment +
                ", balance=" + balance +
                ", responsible=" + responsible +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return Objects.equals(id, client.id);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
