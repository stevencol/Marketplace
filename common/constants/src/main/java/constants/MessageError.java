package constants;

public final class MessageError {

    private MessageError() {
        throw new IllegalStateException(MENSAJE_NO_INSTANCIAR);
    }


    //Mensajes generales
    public static final String MENSAJE_NO_INSTANCIAR = "Esta clase no debe ser instanciada";
    public static final String VALIDACION_FALLIDA = "La validación ha fallado. Por favor, corrija los siguientes campos.";
    public static final String SOLICITUD_COMPLETADA = "La solicitud se completó correctamente.";
    public static final String SOLICITUD_ERROR = "Error al realizar la  solicitud.";

    //Mensajes de validación de campos
    public static final String CAMPO_OBLIGATORIO = "Este campo es obligatorio";
    public static final String CAMPO_NO_VACIO = "Este campo no puede estar vacío";
    public static final String CAMPO_INVALIDO = "Este campo contiene un valor inválido";
    public static final String CAMPO_NUMERICO = "Este campo debe ser numérico";
    public static final String CAMPO_FECHA_INVALIDA = "La fecha proporcionada no es válida";
    public static final String CAMPO_VALOR_MAYOR = "El monto debe ser mayor a 0";
    public static final String ESTADO_TRANSACCION_INVALIDO = "Solo se permiten RECHAZADA o ANULADA";


    //Mesages de Excepciones
    public static final String ENTIDAD_NO_ENCONTRADA = "No se encontraron resultados con id %s";
    public static final String METODO_NO_SOPORTADO = "Método %s no es soportado, solo se permite los siguientes métodos: %s";
    public static final String SALDO_INSUFICIENTE = "Saldo insuficiente para realizar la transacción";
    public static final String EXPIRACION_TARJETA = "La tarjeta ha expirado y no se puede procesar la transacción";
    public static final String ANULACION_NO_PERMITIDA = "La transacción no puede ser anulada en su estado actual";
}
