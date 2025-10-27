package co.bankinc.usecase;

import co.bankinc.model.card.gateways.ICardRepository;
import co.bankinc.model.card.model.CardModel;
import co.bankinc.model.card.model.CardRechargeModel;
import co.bankinc.usecase.usecase.CardUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CardUseCaseTest {

    @Mock
    private ICardRepository cardRepository;

    @InjectMocks
    private CardUseCase cardUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCard_Success() {

        CardModel cardModel = getCardModelTest();
        CardModel savedCard = getCardModelTest();
        savedCard.setCardNumber("1234567890123456");
        savedCard.setExpirationDate(LocalDateTime.now().plusYears(3));
        savedCard.setBalance(BigDecimal.ZERO);

        when(cardRepository.createCard(any(CardModel.class))).thenReturn(savedCard);

        CardModel result = cardUseCase.createCard(cardModel);

        assertNotNull(result);
        assertEquals("1234567890123456", result.getCardNumber());
        assertEquals(BigDecimal.ZERO, result.getBalance());
        assertTrue(result.getExpirationDate().isAfter(LocalDateTime.now()));

        verify(cardRepository).createCard(any(CardModel.class));
    }



    @Test
    void rechargeCard_Success() {
        String cardNumber = "1234567890123456";
        BigDecimal rechargeAmount = new BigDecimal("100.00");
        CardRechargeModel rechargeModel = CardRechargeModel.builder()
                .cardNumber(cardNumber)
                .amount(rechargeAmount)
                .build();

        CardModel existingCard = getCardModelTest();
        existingCard.setBalance(new BigDecimal("50.00"));

        CardModel updatedCard = getCardModelTest();
        updatedCard.setBalance(new BigDecimal("150.00"));

        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(existingCard);
        when(cardRepository.edit(any(CardModel.class))).thenReturn(updatedCard);


        CardRechargeModel result = cardUseCase.rechargeCard(rechargeModel);


        assertNotNull(result);
        assertEquals(cardNumber, result.getCardNumber());
        assertEquals(new BigDecimal("150.00"), result.getAmount());

        verify(cardRepository).findByCardNumber(cardNumber);
        verify(cardRepository).edit(any(CardModel.class));
    }



    @Test
    void findByCardNumber_Success() {
        String cardNumber = "1234567890123456";
        CardModel expectedCard = getCardModelTest();

        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(expectedCard);

        CardModel result = cardUseCase.findByCardNumber(cardNumber);

        assertNotNull(result);
        assertEquals(expectedCard, result);
        verify(cardRepository).findByCardNumber(cardNumber);
    }

    @Test
    void findByCardNumber_NotFound() {
        String cardNumber = "9999999999999999";

        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(null);

        CardModel result = cardUseCase.findByCardNumber(cardNumber);

        assertNull(result);
        verify(cardRepository).findByCardNumber(cardNumber);
    }

    @Test
    void editCard_Success() {
        CardModel cardModel = getCardModelTest();
        cardModel.setBalance(new BigDecimal("200.00"));

        CardModel updatedCard = getCardModelTest();
        updatedCard.setBalance(new BigDecimal("200.00"));

        when(cardRepository.edit(any(CardModel.class))).thenReturn(updatedCard);


        CardModel result = cardUseCase.edit(cardModel);

        assertNotNull(result);
        assertEquals(new BigDecimal("200.00"), result.getBalance());
        verify(cardRepository).edit(any(CardModel.class));
    }

    private CardModel getCardModelTest() {
        return CardModel.builder()
                .id(1L)
                .cardNumber("1234567890123456")
                .holderName("Juan Perez")
                .expirationDate(LocalDateTime.now().plusYears(3))
                .balance(new BigDecimal("100.00"))
                .cardType("CREDITO")
                .cardProduct("MONSTER_CARD")
                .createdAt(LocalDateTime.now())
                .build();
    }
}