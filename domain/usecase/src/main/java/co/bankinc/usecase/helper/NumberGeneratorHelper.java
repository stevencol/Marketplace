package co.bankinc.usecase.helper;

import java.security.SecureRandom;

public class NumberGeneratorHelper {


    private static final SecureRandom random = new SecureRandom();

    private  NumberGeneratorHelper() {
        throw new IllegalStateException("Utility class");
    }



    public static String generateNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digito = random.nextInt(10);
            sb.append(digito);
        }
        return sb.toString();
    }
}
