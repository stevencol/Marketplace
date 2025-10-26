package co.bankinc.model.card.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CardModel  implements Serializable {

    private Long id;
    private String cardNumber;
    private String holderName;
    private LocalDateTime expirationDate;
    private BigDecimal balance;

    private LocalDateTime createdAt;
    private String cardType;
    private String cardProduct;
}
