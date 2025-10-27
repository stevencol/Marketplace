package co.bankinc.model.card.gateways;


import co.bankinc.model.card.model.CardModel;

public interface ICardRepository {

    CardModel createCard(CardModel cardModel);

    CardModel edit(CardModel cardModel);

    CardModel findByCardNumber(String cardNumber);


}
