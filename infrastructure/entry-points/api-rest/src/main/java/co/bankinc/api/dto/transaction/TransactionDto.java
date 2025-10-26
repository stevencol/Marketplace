package co.bankinc.api.dto.transaction;

import co.bankinc.api.dto.card.CardDto;
import co.bankinc.api.valid.CardNotNull;
import co.bankinc.usecase.enun.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static constants.MessageError.*;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor

public class TransactionDto implements Serializable {

    @JsonProperty("id")
    private Long id;


    @NotNull
    @JsonProperty("amount")
    @DecimalMin(value = "0.01", message = CAMPO_VALOR_MAYOR)
    private BigDecimal amount;


    @JsonProperty("transaction_date")
    private LocalDateTime transactionDate;

    @JsonProperty("card")
    @CardNotNull
    private CardDto card;

    @JsonProperty("transaction_status")
    private TransactionStatus transactionStatus;

    @JsonProperty("products")
    @NotNull(message = CAMPO_NO_VACIO)
    @NotEmpty(message = CAMPO_OBLIGATORIO)
    private List<Long> products;
}
