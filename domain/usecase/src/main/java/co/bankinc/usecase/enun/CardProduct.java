package co.bankinc.usecase.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardProduct {


    COLOMBIAN_EXPRESS(123456),
    MONSTER_CARD(654321),
    PASSPORT_CARD(456789),;

    private final int idProducto;

}
