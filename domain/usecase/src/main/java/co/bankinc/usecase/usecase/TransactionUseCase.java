package co.bankinc.usecase.usecase;

import co.bankinc.model.card.gateways.in.ITransactionUseCase;
import co.bankinc.model.card.gateways.out.ICardRepository;
import co.bankinc.model.card.gateways.out.ITransactionRepository;
import co.bankinc.model.card.model.CardModel;
import co.bankinc.model.card.model.ProductTransactionModel;
import co.bankinc.model.card.model.TransactionModel;
import co.bankinc.usecase.enun.TransactionStatus;
import co.bankinc.usecase.exception.AnulacionException;
import co.bankinc.usecase.exception.ExpirationDateException;
import co.bankinc.usecase.exception.SaldoInsuficienteException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class TransactionUseCase implements ITransactionUseCase {

    private final ITransactionRepository transactionRepository;
    private final ICardRepository cardRepository;

    @Override
    public TransactionModel createTransaction(TransactionModel transactionModel) {
        CardModel cardModel = cardRepository.findByCardNumber(transactionModel.getCard().getCardNumber());
        transactionModel.setCard(cardModel);
        transactionModel.setTransactionDate(LocalDateTime.now());

        if (cardModel.getBalance().compareTo(transactionModel.getAmount()) < 0) {
            transactionModel.setTransactionStatus(String.valueOf(TransactionStatus.RECHAZADA));
            transactionModel = transactionRepository.createTransaction(transactionModel);
            throw new SaldoInsuficienteException(transactionModel);
        }

        if (cardModel.getExpirationDate().isBefore(LocalDateTime.now())) {
            transactionModel.setTransactionStatus(String.valueOf(TransactionStatus.RECHAZADA));
            transactionModel = transactionRepository.createTransaction(transactionModel);
            throw new ExpirationDateException(transactionModel);
        }
        cardModel.setBalance(cardModel.getBalance().subtract(transactionModel.getAmount()));
        cardModel = cardRepository.edit(cardModel);
        transactionModel.setCard(cardModel);
        transactionModel.setTransactionStatus(String.valueOf(TransactionStatus.EXITOSA));
        transactionModel = transactionRepository.createTransaction(transactionModel);
        return transactionModel;
    }

    @Override
    public TransactionModel updateTransaction(Long id, String status) {
        TransactionModel transactionModel = transactionRepository.findById(id);
        List<ProductTransactionModel> products = transactionModel.getProductsTransaction();


        boolean isReversed = status.equals(TransactionStatus.ANULADA.name());
        boolean hoursWithin24 = Duration.between(transactionModel.getCreatedAt(), LocalDateTime.now()).toHours() < 24;
        boolean revertBalance = transactionModel.getTransactionStatus().equals(TransactionStatus.EXITOSA.name());


        if (!revertBalance || !isReversed || !hoursWithin24) {
            throw new AnulacionException(transactionModel);
        }

        transactionModel.setTransactionStatus(status);
        transactionModel = transactionRepository.updateTransaction(transactionModel);
        CardModel card = cardRepository.findByCardNumber(transactionModel.getCard().getCardNumber());
        card.setBalance(card.getBalance().add(transactionModel.getAmount()));
        cardRepository.edit(card);

        transactionModel.setProductsTransaction(products);
        return transactionModel;
    }

    @Override
    public TransactionModel getTransaction(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<TransactionModel> getTransactions() {
        return transactionRepository.getTransactions();
    }


}
