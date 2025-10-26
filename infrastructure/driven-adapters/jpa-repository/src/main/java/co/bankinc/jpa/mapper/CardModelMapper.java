package co.bankinc.jpa.mapper;

import co.bankinc.jpa.entity.CardEntity;
import co.bankinc.model.card.model.CardModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface CardModelMapper {


    public CardModel toModel(CardEntity cardModel);

    public CardEntity toEntity(CardModel cardModel);
}
