package logica;

/**
 * ValidadorCupo verifica que una reserva no exceda cupo disponible de una clase particular.
 * Implementa la interfaz ValidadorReserva.
 */
public class ValidadorCupo implements ValidadorReserva {

    /**
     * Valida que la clase asociada a la reserva tenga cupos disponibles.
     *
     * @param reserva La reserva que se desea validar.
     * @return true si hay cupos disponibles.
     * @throws CupoExcedidoException Si no hay cupos disponibles en la clase.
     */
    @Override
    public boolean validar(Reserva reserva) throws CupoExcedidoException {
        ClaseParticular clase = reserva.getClase();

        if (clase.getCuposDisponibles() == 0) {
            throw new CupoExcedidoException("No hay cupo disponible para esta clase.");
        }

        return true;
    }
}