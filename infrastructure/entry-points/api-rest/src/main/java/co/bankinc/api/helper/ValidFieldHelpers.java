package co.bankinc.api.helper;


import co.bankinc.api.dto.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static constants.MessageError.MENSAJE_NO_INSTANCIAR;
import static constants.MessageError.VALIDACION_FALLIDA;
import static co.bankinc.api.helper.ResponseHelper.createResponseErrorFields;

public final class ValidFieldHelpers {


    private ValidFieldHelpers() {
        throw new UnsupportedOperationException(MENSAJE_NO_INSTANCIAR);
    }

    public static ResponseEntity<ApiResponseDto<Object>> validateFields(BindingResult result) {
        Map<String, Object> fields = new HashMap<>();
        result.getFieldErrors().forEach(error -> fields.put(error.getField(), error.getDefaultMessage()));

        return createResponseErrorFields(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.toString(),
                VALIDACION_FALLIDA,
                fields);
    }


}
