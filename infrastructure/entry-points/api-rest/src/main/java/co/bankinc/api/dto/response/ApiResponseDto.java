package co.bankinc.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ApiResponseDto<T>(T data,
                                String status,
                                String message,
                                String error,
                                Map<String, Object> fields) {
}
