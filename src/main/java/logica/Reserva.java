package logica;

import java.time.LocalDateTime;

/**
 * Representa una reserva de una clase particular realizada por un cliente.
 * Esta clase contiene la informacion sobre el cliente y la clase reservada,
 * tambien la fecha de la reserva,tambien el estado de la reserva y el metodo de pago que se uso.
 */
public class Reserva {

    /**
     * Enum que representa los posibles estados de una reserva.
     */
    public enum Estado {
        CONFIRMADA,  /**< Reservaconfirmada. */
        CANCELADA   /**< Reserva ha sido cancelada. */
    }

    /**
     * Enum que representa los posibles metodos de pago para una reserva.
     */
    public enum MetodoPago {
        EFECTIVO,        /**< Efectivo o "cash" */
        TARJETA_DEBITO,  /**< Tarjeta de debito. */
        TARJETA_CREDITO  /**< Tarjeta de credito. */
    }

    private Cliente cliente;
    private ClaseParticular clase;
    private LocalDateTime fechaReserva;
    private Estado estado;
    private MetodoPago metodoPago;

    /**
     * Constructor que crea una reserva con los parametros proporcionados.
     *
     * @param cliente Cliente que realiza la reserva.
     * @param clase Clase particular que ha sido reservada.
     * @param fechaReserva Fecha.
     * @param metodoPago Metodo de pago seleccionado por el cliente.
     */
    public Reserva(Cliente cliente, ClaseParticular clase, LocalDateTime fechaReserva, MetodoPago metodoPago) {
        this.cliente = cliente;
        this.clase = clase;
        this.fechaReserva = fechaReserva;
        this.estado = Estado.CONFIRMADA;
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el cliente asociado a esta reserva.
     *
     * @return Cliente que hizo la reserva.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene la clase particular reservada.
     *
     * @return Clase reservada en la reserva.
     */
    public ClaseParticular getClase() {
        return clase;
    }

    /**
     * Obtiene la fecha y hora en que se realizo la reserva.
     *
     * @return Fecha y hora de la reserva.
     */
    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Obtiene el estado actual de la reserva.
     *
     * @return Estado de la reserva.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Cancela la reserva y libera el cupo correspondiente en la clase.
     */
    public void cancelar() {
        this.estado = Estado.CANCELADA;
        clase.liberarCupo();
    }

    /**
     * Verifica si la reserva esta confirmada.
     *
     * @return true si la reserva esta confirmada, false si esta cancelada.
     */
    public boolean estaConfirmada() {
        return estado == Estado.CONFIRMADA;
    }

    /**
     * Obtiene el metodo de pago utilizado para la reserva.
     *
     * @return Metodo de pago de la reserva.
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
}