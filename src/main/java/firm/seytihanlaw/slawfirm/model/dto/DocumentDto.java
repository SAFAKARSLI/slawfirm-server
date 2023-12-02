package firm.seytihanlaw.slawfirm.model.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    private UUID id;

    private String name;

    private byte[] content;

    private Map<String, String> generics = new LinkedHashMap<>();

    @JsonAnySetter
    void setGenerics(String k, String v) {
        this.generics.put(k, v);
    }


}
