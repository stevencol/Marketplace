package co.bankinc.api.mapper;

import co.bankinc.api.dto.card.CardDto;
import co.bankinc.api.enums.CardType;
import co.bankinc.model.card.model.CardModel;
import co.bankinc.usecase.enun.CardProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface CardDtoMapper {


    @Mapping(target = "cardType", source = "cardType", qualifiedByName = "mapCardTypeString")
    @Mapping(target = "cardProduct", source = "cardProduct", qualifiedByName = "mapCardProductString")
    CardModel toModel(CardDto dto);

    @Mapping(target = "cardType", source = "cardType", qualifiedByName = "mapCardTypeEnum")
    @Mapping(target = "cardProduct", source = "cardProduct", qualifiedByName = "mapCardProductEnum")
    @Mapping(target = "expirationDate", source = "expirationDate", qualifiedByName = "mapLocalDateToString")
    CardDto cardDto(CardModel cardModel);


    @Named("mapCardTypeString")
    default String cardType(CardType cardType) {
        return cardType == null ? null : cardType.name();
    }

    @Named("mapCardProductString")
    default String cardProduct(CardProduct cardProduct) {
        return cardProduct == null ? null : cardProduct.name();
    }

    @Named("mapCardTypeEnum")
    default CardType cardType(String cardType) {
        return CardType.valueOf(cardType);
    }

    @Named("mapCardProductEnum")
    default CardProduct cardProduct(String cardType) {
        return CardProduct.valueOf(cardType);
    }


    @Named("mapLocalDateToString")
    default String mapLocalDateToString(LocalDateTime date) {
        return date == null ? null : date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
