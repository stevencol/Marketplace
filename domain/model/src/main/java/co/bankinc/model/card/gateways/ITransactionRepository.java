package co.bankinc.model.card.gateways;

import co.bankinc.model.card.model.TransactionModel;

import java.util.List;

public interface ITransactionRepository {

    TransactionModel createTransaction(TransactionModel transactionModel);

    TransactionModel findById(Long id);

    TransactionModel updateTransaction(TransactionModel transactionModel);

    List<TransactionModel> getTransactions();



}
