package firm.seytihanlaw.slawfirm.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateDocumentRequestModel {

    private String file_id;

    private Map<String, String> generics = new LinkedHashMap<>();

    @JsonAnySetter
    void setGenerics(String k, String v) {
        this.generics.put(k, v);
    }


}
