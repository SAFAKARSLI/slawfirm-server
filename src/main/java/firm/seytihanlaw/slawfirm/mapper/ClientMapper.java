package firm.seytihanlaw.slawfirm.mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import firm.seytihanlaw.slawfirm.model.Client;
import firm.seytihanlaw.slawfirm.model.ClientUpdateRequestModel;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ClientMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapClient(ClientUpdateRequestModel requestModel, @MappingTarget Client client);
}
