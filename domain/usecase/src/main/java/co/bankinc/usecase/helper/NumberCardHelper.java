package co.bankinc.usecase.helper;

import co.bankinc.usecase.enun.CardProduct;

import static co.bankinc.usecase.helper.NumberGeneratorHelper.generateNumber;

public final class NumberCardHelper {

    private NumberCardHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String getCardNumber(String product) {
        return CardProduct.valueOf(product).getIdProducto() + generateNumber();
    }

}
