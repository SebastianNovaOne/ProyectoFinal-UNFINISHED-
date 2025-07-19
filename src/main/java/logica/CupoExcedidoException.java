package logica;

/**
 * Excepcion que lanza cuando se intenta reservar un cupo en una clase
 * y no hay cupos disponibles.
 */
public class CupoExcedidoException extends Exception {

    /**
     * Constructor que crea nueva excepcion con mensaje proporcionado.
     *
     * @param mensaje El mensaje que describe el motivo de la excepcion.
     */
    public CupoExcedidoException(String mensaje) {
        super(mensaje);
    }
}