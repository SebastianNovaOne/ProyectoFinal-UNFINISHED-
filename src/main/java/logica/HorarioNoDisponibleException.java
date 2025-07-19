package logica;

/**
 * Excepcion que lanza cuando horario no esta disponible para reserva.
 */
public class HorarioNoDisponibleException extends Exception {

    /**
     * Constructor que crea nueva excepcion con mensaje proporcionado.
     *
     * @param mensaje mensaje que describe razon de la excepcion.
     */
    public HorarioNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}