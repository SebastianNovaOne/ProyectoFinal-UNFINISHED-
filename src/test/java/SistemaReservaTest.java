import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.*;

public class SistemaReservaTest {

    private SistemaReserva sistema;
    private Cliente cliente;
    private Profesor profesor;
    private ClaseParticular clase;

    @BeforeEach
    void setUp() {
        sistema = SistemaReserva.getInstancia();
        sistema.getClientes().clear();
        sistema.getProfesores().clear();
        sistema.getClases().clear();
        sistema.getReservas().clear();

        cliente = new Cliente("Pepe", "Pepe@java.com");
        profesor = new Profesor("Pedro", Prueba.MATEMATICA_M1);
        clase = new ClaseParticular(Prueba.MATEMATICA_M1, profesor, java.time.LocalDateTime.now().plusDays(1), 60, 1);

        sistema.agregarCliente(cliente);
        sistema.agregarProfesor(profesor);
        sistema.agregarClase(clase);
    }

    @Test
    void testCrearReservaExitosa() throws Exception {
        Reserva reserva = sistema.crearReserva(cliente, clase, Reserva.MetodoPago.TARJETA_CREDITO);
        assertNotNull(reserva);
        assertTrue(sistema.getReservas().contains(reserva));
        assertEquals(0, clase.getCuposDisponibles());
        assertTrue(cliente.getHistorialReservas().contains(reserva));
    }

    @Test
    void testCrearReservaCupoExcedido() {
        try {
            sistema.crearReserva(cliente, clase, Reserva.MetodoPago.TARJETA_CREDITO);
            sistema.crearReserva(cliente, clase, Reserva.MetodoPago.TARJETA_CREDITO);
            fail("Se esperaba CupoExcedidoException");
        } catch (CupoExcedidoException e) {
            assertEquals("No hay cupo disponible para esta clase.", e.getMessage());
        }
    }

    @Test
    void testCancelarReservaExitosa() throws Exception {
        Reserva reserva = sistema.crearReserva(cliente, clase, Reserva.MetodoPago.TARJETA_CREDITO);
        sistema.cancelarReserva(reserva);
        assertFalse(reserva.confirmar());
    }

    @Test
    void testCancelarReservaNoEncontrada() {
        Reserva reserva = new Reserva(cliente, clase, java.time.LocalDateTime.now(), Reserva.MetodoPago.TARJETA_CREDITO);
        try {
            sistema.cancelarReserva(reserva);
            fail("Se esperaba una excepción por reserva no encontrada");
        } catch (Exception e) {
            assertEquals("Reserva no encontrada.", e.getMessage());
        }
    }
}