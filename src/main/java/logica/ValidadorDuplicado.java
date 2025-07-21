package logica;

import java.util.List;

/**
 * Validador que verifica si un cliente ya tiene una reserva confirmada
 * para la misma clase, evitando reservas duplicadas.
 * Usa patron Strategy a traves de la interfaz ValidadorReserva.
 */
public class ValidadorDuplicado implements ValidadorReserva {

    /**
     * Valida que no exista una reserva confirmada duplicada para la misma clase.
     *
     * @param reserva La reserva a validar.
     * @return true si no existe reserva duplicada.
     * @throws ReservaDuplicadaException si el cliente ya tiene una reserva confirmada para la clase.
     */
    @Override
    public boolean validar(Reserva reserva) throws ReservaDuplicadaException {
        Cliente cliente = reserva.getCliente();
        ClaseParticular clase = reserva.getClase();

        List<Reserva> historial = cliente.getHistorialReservas();

        for (Reserva r : historial) {
            if (reserva.getClase().equals(clase) && r.getEstado().equals("Confirmada")) {
                throw new ReservaDuplicadaException("El cliente ya tiene una reserva confirmada para esta clase.");
            }
        }
        return true;
    }
}