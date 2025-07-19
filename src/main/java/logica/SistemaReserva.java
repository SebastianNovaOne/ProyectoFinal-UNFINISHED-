package logica;

import java.util.ArrayList;
import java.util.List;

public class SistemaReserva {
    private static SistemaReserva instancia;

    private List<Cliente> clientes;
    private List<Profesor> profesores;
    private List<ClaseParticular> clases;
    private List<Reserva> reservas;

    private SistemaReserva() {
        clientes = new ArrayList<>();
        profesores = new ArrayList<>();
        clases = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public static SistemaReserva getInstancia() {
        if (instancia == null) {
            instancia = new SistemaReserva();
        }
        return instancia;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public void agregarClase(ClaseParticular clase) {
        clases.add(clase);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public List<ClaseParticular> getClases() {
        return clases;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Reserva crearReserva(Cliente cliente, ClaseParticular clase, Reserva.MetodoPago metodoPago) throws CupoExcedidoException {
        if (clase.getCuposDisponibles() == 0) {
            throw new CupoExcedidoException("No hay cupo disponible para esta clase.");
        }

        Reserva reserva = new Reserva(cliente, clase, java.time.LocalDateTime.now(), metodoPago);
        reservas.add(reserva);
        clase.reservarCupo();
        cliente.agregarReserva(reserva);
        return reserva;
    }

    public void cancelarReserva(Reserva reserva) throws Exception {
        if (!reservas.contains(reserva)) {
            throw new Exception("Reserva no encontrada.");
        }
        reserva.cancelar();
    }

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