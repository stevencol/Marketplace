package co.bankinc.api.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static constants.MessageError.CAMPO_OBLIGATORIO;

@Documented
@Constraint(validatedBy = CardNotNullValid.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNotNull {

    String message() default "Numero de tarjeta:  " + CAMPO_OBLIGATORIO;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
