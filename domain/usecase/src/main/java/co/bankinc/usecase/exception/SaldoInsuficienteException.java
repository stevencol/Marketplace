package co.bankinc.usecase.exception;

import co.bankinc.model.card.model.TransactionModel;
import lombok.Getter;

@Getter
public class SaldoInsuficienteException extends RuntimeException {

    private final TransactionModel transactionModel;

    public SaldoInsuficienteException(TransactionModel transactionModel) {
        this.transactionModel = transactionModel;

    }
}

