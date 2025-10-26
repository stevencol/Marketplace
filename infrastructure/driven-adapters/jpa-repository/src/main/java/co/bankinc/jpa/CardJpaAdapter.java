package co.bankinc.jpa;

import co.bankinc.jpa.entity.CardEntity;
import co.bankinc.jpa.mapper.CardModelMapper;
import co.bankinc.model.card.gateways.out.ICardRepository;
import co.bankinc.model.card.model.CardModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static constants.MessageError.ENTIDAD_NO_ENCONTRADA;

@AllArgsConstructor
@Component("cardJpaAdapter")

public class CardJpaAdapter implements ICardRepository {

    private final CardJPARepository cardRepository;
    private final CardModelMapper cardMapper;

    @Override
    public CardModel createCard(CardModel cardModel) {
        CardEntity cardEntity = cardMapper.toEntity(cardModel);
        return cardMapper.toModel(cardRepository.save(cardEntity));
    }

    @Override
    public CardModel edit(CardModel card) {
        CardEntity cardSave = cardMapper.toEntity(card);
        return cardMapper.toModel(cardRepository.save(cardSave));

    }

    @Override
    public CardModel findByCardNumber(String cardNumber) {
        CardEntity cardEntity = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ENTIDAD_NO_ENCONTRADA, cardNumber)));
        return cardMapper.toModel(cardEntity);
    }


}
