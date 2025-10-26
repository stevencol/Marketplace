package co.bankinc.model.card.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)

public class TransactionModel implements Serializable {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private CardModel card;
    private String transactionStatus;
    private List<ProductTransactionModel> productsTransaction;
    private LocalDateTime createdAt;

}
