package co.bankinc.model.card.gateways.in;

import co.bankinc.model.card.model.CardModel;
import co.bankinc.model.card.model.CardRechargeModel;

public interface ICardUseCase {

    CardModel createCard(CardModel cardModel);
    CardRechargeModel rechargeCard(CardRechargeModel cardRecharge);
    CardModel findByCardNumber(String cardNumber);
    CardModel edit(CardModel cardModel);

}
