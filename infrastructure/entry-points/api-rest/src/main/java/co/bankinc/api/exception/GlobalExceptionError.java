package co.bankinc.api.exception;

import co.bankinc.api.dto.response.ApiResponseDto;
import co.bankinc.api.mapper.TransactionDtoMapper;
import co.bankinc.usecase.exception.AnulacionException;
import co.bankinc.usecase.exception.ExpirationDateException;
import co.bankinc.usecase.exception.SaldoInsuficienteException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static co.bankinc.api.helper.ResponseHelper.createResponseError;
import static co.bankinc.api.helper.ResponseHelper.createResponseOk;
import static co.bankinc.api.helper.ValidFieldHelpers.validateFields;
import static constants.MessageError.*;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionError {

    private final TransactionDtoMapper transactionMapper;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseDto<Object>> numberFormatException(HttpMessageNotReadableException ex) {
        return createResponseError(HttpStatus.BAD_REQUEST, SOLICITUD_ERROR, ex.getMessage());

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return createResponseError(HttpStatus.NOT_FOUND, ENTIDAD_NO_ENCONTRADA, ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String errorMsg = String.format(METODO_NO_SOPORTADO, ex.getMethod(), ex.getSupportedHttpMethods());
        return createResponseError(HttpStatus.METHOD_NOT_ALLOWED, errorMsg, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return validateFields(ex.getBindingResult());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleNullPointerException(NullPointerException ex) {

        return createResponseOk(HttpStatus.NOT_FOUND, "NullPointerException", ex.getMessage());

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {

        return createResponseOk(HttpStatus.BAD_REQUEST, "SQLIntegrityConstraintViolationException", ex.getMessage());


    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleSQLException(SQLException ex) {

        return createResponseOk(HttpStatus.BAD_REQUEST, "SQLException", ex.getMessage());


    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        return createResponseOk(HttpStatus.BAD_REQUEST, transactionMapper.toDto(ex.getTransactionModel()), SALDO_INSUFICIENTE);
    }

    @ExceptionHandler(ExpirationDateException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleExpirationDateException(ExpirationDateException ex) {
        return createResponseOk(HttpStatus.BAD_REQUEST, transactionMapper.toDto(ex.getTransactionModel()), EXPIRACION_TARJETA);
    }

    @ExceptionHandler(AnulacionException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleAnulacionException(AnulacionException ex) {
        return createResponseOk(HttpStatus.BAD_REQUEST, transactionMapper.toDto(ex.getTransactionModel()), ANULACION_NO_PERMITIDA);
    }


}


