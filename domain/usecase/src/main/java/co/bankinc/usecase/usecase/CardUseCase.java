package co.bankinc.usecase.usecase;

import co.bankinc.model.card.gateways.in.ICardUseCase;
import co.bankinc.model.card.gateways.ICardRepository;
import co.bankinc.model.card.model.CardModel;
import co.bankinc.model.card.model.CardRechargeModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import static co.bankinc.usecase.helper.NumberCardHelper.getCardNumber;
import static co.bankinc.usecase.helper.DateExpirationDate.getExpirationDate;

@AllArgsConstructor
@Data
public class CardUseCase implements ICardUseCase {


    private final ICardRepository cardRepository;

    public CardModel createCard(CardModel cardModel) {
        cardModel.setCardNumber(getCardNumber(cardModel.getCardProduct()));
        cardModel.setExpirationDate(getExpirationDate());
        cardModel.setBalance(BigDecimal.valueOf(0.0));

        return cardRepository.createCard(cardModel);
    }

    @Override
    public CardRechargeModel rechargeCard(CardRechargeModel cardRecharge) {
        CardModel cardModel = findByCardNumber(cardRecharge.getCardNumber());
        cardModel.setBalance(cardModel.getBalance().add(cardRecharge.getAmount()));
        CardModel cardUpdate = cardRepository.edit(cardModel);
        return CardRechargeModel.builder().cardNumber(cardUpdate.getCardNumber()).amount(cardUpdate.getBalance()).build();
    }

    @Override
    public CardModel findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public CardModel edit(CardModel cardModel) {
        CardModel cardUpdate = new CardModel();
        cardUpdate.setBalance(cardModel.getBalance());
        return cardRepository.edit(cardModel);
    }

}
