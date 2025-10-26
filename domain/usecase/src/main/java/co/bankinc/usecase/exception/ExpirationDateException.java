package co.bankinc.usecase.exception;

import co.bankinc.model.card.model.TransactionModel;
import lombok.Getter;

@Getter
public class ExpirationDateException extends RuntimeException {
    private final TransactionModel transactionModel;

    public ExpirationDateException(TransactionModel transactionModel) {
        this.transactionModel = transactionModel;
    }


}
