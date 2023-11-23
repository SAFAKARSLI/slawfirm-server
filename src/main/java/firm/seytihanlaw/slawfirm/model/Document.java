package firm.seytihanlaw.slawfirm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Document {

    @Id
//  @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String fileName;

    @Column(length = 999999)
    private byte[] fileContent;

}
