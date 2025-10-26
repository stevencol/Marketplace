package co.bankinc.usecase.exception;

import co.bankinc.model.card.model.TransactionModel;
import lombok.Getter;

@Getter
public class AnulacionException extends RuntimeException {


    private  final TransactionModel transactionModel;

    public AnulacionException(TransactionModel transactionModel) {
        this.transactionModel = transactionModel;

    }
}
