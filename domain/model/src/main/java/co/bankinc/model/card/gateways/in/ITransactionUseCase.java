package co.bankinc.model.card.gateways.in;

import co.bankinc.model.card.model.TransactionModel;

import java.util.List;

public interface ITransactionUseCase {

    TransactionModel createTransaction(TransactionModel transactionModel);

    TransactionModel updateTransaction(Long id, String status);

    TransactionModel getTransaction(Long id);
    List<TransactionModel> getTransactions();
}
