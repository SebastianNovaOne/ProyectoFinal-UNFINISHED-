package logica;

import java.time.LocalDateTime;

public class Reserva {
    public enum Estado {
        CONFIRMADA,
        CANCELADA
    }

    public enum MetodoPago {
        EFECTIVO,
        TARJETA_DEBITO,
        TARJETA_CREDITO
    }

    private Cliente cliente;
    private ClaseParticular clase;
    private LocalDateTime fechaReserva;
    private Estado estado;
    private MetodoPago metodoPago;
}