package firm.seytihanlaw.slawfirm.model.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    private String file_id;

    private Map<String, String> generics = new LinkedHashMap<>();

    @JsonAnySetter
    void setGenerics(String k, String v) {
        this.generics.put(k, v);
    }


}
