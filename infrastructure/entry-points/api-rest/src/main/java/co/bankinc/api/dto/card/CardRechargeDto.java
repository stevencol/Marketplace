package co.bankinc.api.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

import static constants.MessageError.CAMPO_OBLIGATORIO;
import static constants.MessageError.CAMPO_VALOR_MAYOR;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardRechargeDto {

    @NotBlank(message = CAMPO_OBLIGATORIO)
    @JsonProperty("card_number")
    private String cardNumber;

    @NotNull
    @JsonProperty("amount")
    @DecimalMin(value = "0.01", message = CAMPO_VALOR_MAYOR)
    private BigDecimal amount;
}
