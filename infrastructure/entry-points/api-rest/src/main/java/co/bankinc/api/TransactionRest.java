package co.bankinc.api;

import co.bankinc.api.dto.response.ApiResponseDto;
import co.bankinc.api.dto.transaction.TransactionDto;

import co.bankinc.api.dto.transaction.TransactionUpdateDto;
import co.bankinc.api.mapper.TransactionDtoMapper;
import co.bankinc.model.card.model.TransactionModel;
import co.bankinc.usecase.usecase.TransactionUseCase;
import jakarta.persistence.EntityListeners;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static co.bankinc.api.helper.ResponseHelper.createResponseOk;
import static co.bankinc.api.helper.ValidFieldHelpers.validateFields;
import static constants.MessageInfo.OPERACION_EXITOSA;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
@EntityListeners(AuditingEntityListener.class)
public class TransactionRest {

    private final TransactionUseCase transactionUseCase;
    private final TransactionDtoMapper transactionMapper;

    @PostMapping
    public ResponseEntity<ApiResponseDto<Object>> createTransaction(@Valid @RequestBody TransactionDto transaction, BindingResult result) {
        if (result.hasErrors()) {
            return validateFields(result);
        }
        TransactionModel transactionModel = transactionMapper.toModel(transaction);
        transactionModel = transactionUseCase.createTransaction(transactionModel);
        return createResponseOk(HttpStatus.CREATED, transactionMapper.toDto(transactionModel), OPERACION_EXITOSA);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto<Object>> editTransaction(@Valid @RequestBody TransactionUpdateDto transaction, BindingResult result) {

        if (result.hasErrors()) {
            return validateFields(result);
        }
        TransactionModel transactionModel = transactionUseCase.updateTransaction(transaction.getId(), String.valueOf(transaction.getTransactionStatus()));
        return createResponseOk(HttpStatus.OK, transactionMapper.toDto(transactionModel), OPERACION_EXITOSA);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<Object>> getTransactions() {
        return createResponseOk(HttpStatus.OK, transactionUseCase.getTransactions().stream().map(transactionMapper::toDto).toList(), OPERACION_EXITOSA);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Object>> getTransactionById(@PathVariable Long id) {
        TransactionModel transactionModel = transactionUseCase.getTransaction(id);
        return createResponseOk(HttpStatus.OK, transactionMapper.toDto(transactionModel), OPERACION_EXITOSA);
    }


}
