package co.bankinc.usecase;
import co.bankinc.model.card.gateways.out.ICardRepository;
import co.bankinc.model.card.gateways.out.ITransactionRepository;
import co.bankinc.model.card.model.CardModel;
import co.bankinc.model.card.model.TransactionModel;
import co.bankinc.usecase.enun.TransactionStatus;
import co.bankinc.usecase.exception.AnulacionException;
import co.bankinc.usecase.exception.ExpirationDateException;
import co.bankinc.usecase.exception.SaldoInsuficienteException;
import co.bankinc.usecase.usecase.TransactionUseCase;
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

class TransactionUseCaseTest {

    @Mock
    private ITransactionRepository transactionRepository;

    @Mock
    private ICardRepository cardRepository;

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_Success() {
        TransactionModel transactionModel = getTransactionModelTest();
        CardModel cardModel = getCardModelTest();
        cardModel.setBalance(new BigDecimal("200.00"));
        cardModel.setExpirationDate(LocalDateTime.now().plusYears(1));

        TransactionModel savedTransaction = getTransactionModelTest();
        savedTransaction.setTransactionStatus(TransactionStatus.EXITOSA.name());

        when(cardRepository.findByCardNumber(any())).thenReturn(cardModel);
        when(transactionRepository.createTransaction(any())).thenReturn(savedTransaction);
        when(cardRepository.edit(any())).thenReturn(cardModel);

        TransactionModel result = transactionUseCase.createTransaction(transactionModel);

        assertNotNull(result);
        assertEquals(TransactionStatus.EXITOSA.name(), result.getTransactionStatus());
        verify(cardRepository).findByCardNumber(any());
        verify(transactionRepository).createTransaction(any());
        verify(cardRepository).edit(any());
    }

    @Test
    void createTransaction_SaldoInsuficiente() {
        TransactionModel transactionModel = getTransactionModelTest();
        transactionModel.setAmount(new BigDecimal("200.00"));

        CardModel cardModel = getCardModelTest();
        cardModel.setBalance(new BigDecimal("50.00"));

        cardModel.setExpirationDate(LocalDateTime.now().plusYears(1));

        TransactionModel rejectedTransaction = getTransactionModelTest();
        rejectedTransaction.setTransactionStatus(TransactionStatus.RECHAZADA.name());

        when(cardRepository.findByCardNumber(any())).thenReturn(cardModel);
        when(transactionRepository.createTransaction(any())).thenReturn(rejectedTransaction);

        assertThrows(SaldoInsuficienteException.class, () -> {
            transactionUseCase.createTransaction(transactionModel);
        });

        verify(cardRepository).findByCardNumber(any());
        verify(transactionRepository).createTransaction(any());
        verify(cardRepository, never()).edit(any());
    }

    @Test
    void createTransaction_TarjetaExpirada() {


        TransactionModel transactionModel = getTransactionModelTest();
        CardModel cardModel = getCardModelTest();
        cardModel.setBalance(new BigDecimal("200.00"));
        cardModel.setExpirationDate(LocalDateTime.now().minusDays(1));


        TransactionModel rejectedTransaction = getTransactionModelTest();
        rejectedTransaction.setTransactionStatus(TransactionStatus.RECHAZADA.name());

        when(cardRepository.findByCardNumber(any())).thenReturn(cardModel);
        when(transactionRepository.createTransaction(any())).thenReturn(rejectedTransaction);


        assertThrows(ExpirationDateException.class, () -> {
            transactionUseCase.createTransaction(transactionModel);
        });

        verify(cardRepository).findByCardNumber(any());
        verify(transactionRepository).createTransaction(any());
        verify(cardRepository, never()).edit(any());
    }

    @Test
    void updateTransaction_Success() {

        Long transactionId = 1L;
        String newStatus = TransactionStatus.ANULADA.name();

        TransactionModel existingTransaction = getTransactionModelTest();
        existingTransaction.setTransactionStatus(TransactionStatus.EXITOSA.name());
        existingTransaction.setCreatedAt(LocalDateTime.now().minusHours(12));

        CardModel cardModel = getCardModelTest();
        cardModel.setBalance(new BigDecimal("50.00"));

        when(transactionRepository.findById(transactionId)).thenReturn(existingTransaction);
        when(transactionRepository.updateTransaction(any())).thenReturn(existingTransaction);
        when(cardRepository.findByCardNumber(any())).thenReturn(cardModel);
        when(cardRepository.edit(any())).thenReturn(cardModel);


        TransactionModel result = transactionUseCase.updateTransaction(transactionId, newStatus);


        assertNotNull(result);
        verify(transactionRepository).findById(transactionId);
        verify(transactionRepository).updateTransaction(any());
        verify(cardRepository).findByCardNumber(any());
        verify(cardRepository).edit(any());
    }

    @Test
    void updateTransaction_AnulacionFueraDePlazo() {


        Long transactionId = 1L;
        String newStatus = TransactionStatus.ANULADA.name();

        TransactionModel existingTransaction = getTransactionModelTest();
        existingTransaction.setTransactionStatus(TransactionStatus.EXITOSA.name());
        existingTransaction.setCreatedAt(LocalDateTime.now().minusHours(25));

        when(transactionRepository.findById(transactionId)).thenReturn(existingTransaction);

        assertThrows(AnulacionException.class, () -> {
            transactionUseCase.updateTransaction(transactionId, newStatus);
        });

        verify(transactionRepository).findById(transactionId);
        verify(transactionRepository, never()).updateTransaction(any());
        verify(cardRepository, never()).edit(any());
    }

    @Test
    void updateTransaction_NoEraExitosa() {

        Long transactionId = 1L;
        String newStatus = TransactionStatus.ANULADA.name();

        TransactionModel existingTransaction = getTransactionModelTest();
        existingTransaction.setTransactionStatus(TransactionStatus.RECHAZADA.name());

        existingTransaction.setCreatedAt(LocalDateTime.now().minusHours(12));

        when(transactionRepository.findById(transactionId)).thenReturn(existingTransaction);

        assertThrows(AnulacionException.class, () -> {
            transactionUseCase.updateTransaction(transactionId, newStatus);
        });

        verify(transactionRepository).findById(transactionId);
        verify(transactionRepository, never()).updateTransaction(any());
        verify(cardRepository, never()).edit(any());
    }

    @Test
    void updateTransaction_NoEsAnulacion() {

        Long transactionId = 1L;
        String newStatus = TransactionStatus.RECHAZADA.name();


        TransactionModel existingTransaction = getTransactionModelTest();
        existingTransaction.setTransactionStatus(TransactionStatus.EXITOSA.name());
        existingTransaction.setCreatedAt(LocalDateTime.now().minusHours(12));

        when(transactionRepository.findById(transactionId)).thenReturn(existingTransaction);


        assertThrows(AnulacionException.class, () -> {
            transactionUseCase.updateTransaction(transactionId, newStatus);
        });

        verify(transactionRepository).findById(transactionId);
        verify(transactionRepository, never()).updateTransaction(any());
        verify(cardRepository, never()).edit(any());
    }

    private TransactionModel getTransactionModelTest() {
        return TransactionModel.builder()
                .id(1L)
                .amount(new BigDecimal("100.00"))
                .transactionDate(LocalDateTime.now())
                .card(getCardModelTest())
                .transactionStatus(TransactionStatus.EXITOSA.name())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private CardModel getCardModelTest() {
        return CardModel.builder()
                .id(1L)
                .cardNumber("1234567890123456")
                .holderName("Juan Perez")
                .expirationDate(LocalDateTime.now().plusYears(1))
                .balance(new BigDecimal("150.00"))
                .cardType("CREDITO")
                .cardProduct("MONSTER_CARD")
                .createdAt(LocalDateTime.now())
                .build();
    }
}