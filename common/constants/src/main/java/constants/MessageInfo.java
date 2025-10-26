package constants;

import static constants.MessageError.MENSAJE_NO_INSTANCIAR;

public final class MessageInfo {

    private MessageInfo() {
        throw new IllegalStateException(MENSAJE_NO_INSTANCIAR);
    }

    public static final String OPERACION_EXITOSA = "La operación se realizó exitosamente.";
    public static final String RECARGA_EXITOSA = "La targeta No°  %s fue recargada exitosamente, nuevo saldo: %s usd.";
}
