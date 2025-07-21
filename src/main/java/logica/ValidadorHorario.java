package logica;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Validador que verifica que el horario de una nueva reserva
 * no choque con horarios de reservas confirmadas del cliente.
 * Implementa el patron Strategy a traves de la interfaz ValidadorReserva.
 */
public class ValidadorHorario implements ValidadorReserva {

    /**
     * Valida que el horario de la reserva no choque con ninguna reserva confirmada previa.
     *
     * @param reserva La reserva a validar.
     * @return true si el horario no choca con ninguna reserva confirmada.
     * @throws HorarioNoDisponibleException si el horario choca con otra reserva confirmada.
     */
    @Override
    public boolean validar(Reserva reserva) throws HorarioNoDisponibleException {
        Cliente cliente = reserva.getCliente();
        ClaseParticular claseNueva = reserva.getClase();

        List<Reserva> historial = cliente.getHistorialReservas();

        for (Reserva r : historial) {
            if (r.getEstado().equals("Confirmada")) {
                ClaseParticular claseExistente = r.getClase();
                LocalDateTime inicioNueva = claseNueva.getHorario();
                LocalDateTime finNueva = inicioNueva.plusMinutes(claseNueva.getDuracion());

                LocalDateTime inicioExistente = claseExistente.getHorario();
                LocalDateTime finExistente = inicioExistente.plusMinutes(claseExistente.getDuracion());

                boolean solapan = inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente);

                if (solapan) {
                    throw new HorarioNoDisponibleException("La clase reservada se solapa con otra reserva existente.");
                }
            }
        }

        return true;
    }
}