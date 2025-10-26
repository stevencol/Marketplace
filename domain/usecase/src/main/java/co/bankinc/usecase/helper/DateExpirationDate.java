package co.bankinc.usecase.helper;

import java.time.LocalDateTime;

public final class DateExpirationDate {

    private  DateExpirationDate() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime getExpirationDate() {
        return LocalDateTime.now().plusYears(3);
    }
}
