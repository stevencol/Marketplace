package co.bankinc.api.mapper;

import co.bankinc.api.dto.card.CardRechargeDto;
import co.bankinc.model.card.model.CardRechargeModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface CardRechargeMapper {

    CardRechargeDto toDto(CardRechargeModel cardRecharge);

    CardRechargeModel toModel(CardRechargeDto cardRechargeDto);
}
