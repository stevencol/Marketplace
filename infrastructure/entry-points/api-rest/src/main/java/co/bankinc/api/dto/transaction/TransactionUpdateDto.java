package co.bankinc.api.dto.transaction;

import co.bankinc.usecase.enun.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor

public class TransactionUpdateDto implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("transaction_status")
    private TransactionStatus transactionStatus;


}
