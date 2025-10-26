package co.bankinc.model.card.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor

public class ProductTransactionModel implements Serializable {

    private Long id;

    private Long productId;

    private TransactionModel transactionModel;


}
