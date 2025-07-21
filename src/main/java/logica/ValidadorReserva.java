package logica;

/**
 * Interfaz que define metodo para validar una reserva.
 * Implementa patron de diseno Strategy para permitir
 * diferentes criterios de validacion de reservas.
 */
public interface ValidadorReserva {
    /**
     * Valida una reserva segun criterio especifico.
     *
     * @param reserva La reserva a validar.
     * @return true si reserva es valida segun criterio.
     * @throws Exception Si la validacion no resulta.
     */
    boolean validar(Reserva reserva) throws Exception;
}