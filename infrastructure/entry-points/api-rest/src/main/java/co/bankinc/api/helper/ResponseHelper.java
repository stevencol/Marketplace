package co.bankinc.api.helper;

import co.bankinc.api.dto.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static constants.MessageError.*;

public final class ResponseHelper {

    private ResponseHelper() {
        throw new UnsupportedOperationException(MENSAJE_NO_INSTANCIAR);
    }

    public static <T> ResponseEntity<ApiResponseDto<T>> createResponseErrorFields(HttpStatus httpStatus, String message, String error, Map<String, Object> fields) {
        return ResponseEntity.status(httpStatus).body(new ApiResponseDto<>(null, httpStatus.toString(), message, error, fields));
    }

    public static <T> ResponseEntity<ApiResponseDto<T>> createResponseError(HttpStatus httpStatus, String message, String error) {
        return ResponseEntity.status(httpStatus).body(new ApiResponseDto<>(null, httpStatus.toString(), message, error, null));
    }


    public static <T> ResponseEntity<ApiResponseDto<T>> createResponseOk(HttpStatus httpStatus, T data, String message) {
        return ResponseEntity.status(httpStatus).body(new ApiResponseDto<>(data, httpStatus.toString(), message, null, null));
    }

}


