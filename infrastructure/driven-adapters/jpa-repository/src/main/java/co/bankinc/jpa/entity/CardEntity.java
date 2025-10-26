package co.bankinc.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "cards")
@NoArgsConstructor
@Data

public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "holder_name", nullable = false)
    private String holderName;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "card_product", nullable = false)
    private String cardProduct;

    @JsonManagedReference
    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<TransactionEntity> transactions;


}
