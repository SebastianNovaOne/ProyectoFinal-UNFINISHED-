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
}