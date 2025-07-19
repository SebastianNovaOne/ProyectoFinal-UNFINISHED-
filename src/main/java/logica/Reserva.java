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

    public Reserva(Cliente cliente, ClaseParticular clase, LocalDateTime fechaReserva, MetodoPago metodoPago) {
        this.cliente = cliente;
        this.clase = clase;
        this.fechaReserva = fechaReserva;
        this.estado = Estado.CONFIRMADA;
        this.metodoPago = metodoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ClaseParticular getClase() {
        return clase;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public Estado getEstado() {
        return estado;
    }
}