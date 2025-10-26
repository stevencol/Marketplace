package co.bankinc.api.valid;

import co.bankinc.api.dto.card.CardDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardNotNullValid implements ConstraintValidator<CardNotNull, CardDto> {

    @Override
    public boolean isValid(CardDto value, ConstraintValidatorContext context) {

        if (value == null || value.getCardNumber() == null) {
            return false;
        }
        return !value.getCardNumber().isBlank();
    }
}
