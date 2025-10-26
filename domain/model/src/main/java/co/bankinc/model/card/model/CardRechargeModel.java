package co.bankinc.model.card.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CardRechargeModel {

    private String cardNumber;

    private BigDecimal amount;

}
