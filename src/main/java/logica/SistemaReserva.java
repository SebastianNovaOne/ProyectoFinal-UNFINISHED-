package logica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Esta clase representa el sistema de reservas de clases particulares.
 * Utiliza el patron de diseño Singleton para que solo haya una instancia
 * del sistema de reservas en el programa.
 *
 * El sistema permite agregar clientes, profesores, clases y gestionar reservas,
 * y ademas crear y cancelar reservas, y obtener las reservas activas
 * de un cliente específico.
 */
public class SistemaReserva {
    private static SistemaReserva instancia;

    private List<Cliente> clientes;
    private List<Profesor> profesores;
    private List<ClaseParticular> clases;
    private List<Reserva> reservas;

    /**
     * Constructor privado para evitar creación de instancias fuera del método
     * {@link #getInstancia()}.
     * Inicia las listas de clientes, profesores, clases y reservas.
     */
    private SistemaReserva() {
        clientes = new ArrayList<>();
        profesores = new ArrayList<>();
        clases = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    /**
     * Obtiene la instancia única del sistema de reservas y si no existe la crea.
     *
     * @return La instancia única del sistema de reservas.
     */
    public static SistemaReserva getInstancia() {
        if (instancia == null) {
            instancia = new SistemaReserva();
        }
        return instancia;
    }

    /**
     * Agrega un cliente al sistema de reservas.
     *
     * @param cliente El cliente que se va a agregar.
     */
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Agrega un profesor al sistema de reservas (En el futuro podría reducirlo a un solo profesor).
     *
     * @param profesor El profesor que se va a agregar.
     */
    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    /**
     * Agrega una clase particular al sistema de reservas.
     *
     * @param clase La clase particular que se va a agregar.
     */
    public void agregarClase(ClaseParticular clase) {
        clases.add(clase);
    }

    /**
     * Obtiene la lista de clientes registrados en el sistema.
     *
     * @return La lista de clientes.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Obtiene la lista de profesores registrados en el sistema.
     *
     * @return La lista de profesores.
     */
    public List<Profesor> getProfesores() {
        return profesores;
    }

    /**
     * Obtiene la lista de clases particulares disponibles en el sistema.
     *
     * @return La lista de clases particulares.
     */
    public List<ClaseParticular> getClases() {
        return clases;
    }

    /**
     * Obtiene la lista de reservas realizadas en el sistema.
     *
     * @return La lista de reservas.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Crea una nueva reserva para un cliente y una clase particular.
     * IMPORTANTE: La reserva se confirma solo si hay cupos disponibles en la clase
     * y no hay choque con otras reservas.
     *
     * @param cliente El cliente que realiza la reserva.
     * @param clase La clase que se reserva.
     * @param metodoPago El metodo de pago elegido para la reserva.
     * @return La nueva reserva creada.
     * @throws CupoExcedidoException Ocurre cuando no hay cupos disponibles para la clase.
     * @throws ReservaDuplicadaException Ocurre cuando el cliente ya tiene una reserva confirmada para la misma clase.
     * @throws HorarioNoDisponibleException Ocurre cuando la nueva reserva choca con otra ya confirmada.
     */
    public Reserva crearReserva(Cliente cliente, ClaseParticular clase, Reserva.MetodoPago metodoPago) throws Exception {
        Reserva reserva = new Reserva(cliente, clase, LocalDateTime.now(), metodoPago);

        new ValidadorCupo().validar(reserva);
        new ValidadorDuplicado().validar(reserva);
        new ValidadorHorario().validar(reserva);

        reservas.add(reserva);
        clase.reservarCupo();
        cliente.agregarReserva(reserva);
        return reserva;
    }

    /**
     * Cancela una reserva existente. La reserva debe estar presente en el sistema
     * para ser cancelada.
     *
     * @param reserva La reserva que se desea cancelar.
     * @throws Exception Si la reserva no existe en el sistema.
     */
    public void cancelarReserva(Reserva reserva) throws Exception {
        if (!reservas.contains(reserva)) {
            throw new Exception("Reserva no encontrada.");
        }
        reserva.cancelar();
    }

    /**
     * Obtiene una lista de reservas confirmadas para un cliente específico.
     *
     * @param cliente El cliente del cual se desean obtener las reservas.
     * @return Una lista de reservas confirmadas para el cliente.
     */
    public List<Reserva> getReservasPorCliente(Cliente cliente) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getCliente().equals(cliente) && r.estaConfirmada()) {
                resultado.add(r);
            }
        }
        return resultado;
    }
}