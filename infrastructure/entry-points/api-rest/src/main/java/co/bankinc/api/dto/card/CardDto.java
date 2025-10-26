package co.bankinc.api.dto.card;

import co.bankinc.api.enums.CardType;
import co.bankinc.usecase.enun.CardProduct;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static constants.MessageError.CAMPO_NO_VACIO;
import static constants.MessageError.CAMPO_OBLIGATORIO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("card_number")
    private String cardNumber;

    @JsonProperty(value = "holder_name")
    @NotBlank(message = CAMPO_NO_VACIO)
    private String holderName;

    @JsonProperty(value = "expiration_date")
    private String expirationDate;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty(value = "card_type")
    @NotNull(message = CAMPO_OBLIGATORIO)
    private CardType cardType;


    @JsonProperty("card_product")
    @NotNull(message = CAMPO_OBLIGATORIO)
    private CardProduct cardProduct;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

}
