import logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;
    private ClaseParticular clase;
    private SistemaReserva sistema;
    @BeforeEach
    void setUp() {
        cliente = new Cliente("Juan", "juan@gmail.com");
        Profesor profesor = new Profesor("Profesor", Prueba.MATEMATICA_M1);
        clase = new ClaseParticular(Prueba.MATEMATICA_M1, profesor, LocalDateTime.now().plusDays(1), 60, 2);
        sistema = SistemaReserva.getInstancia();
        sistema.getReservas().clear();
        sistema.getClases().clear();
        sistema.agregarClase(clase);
    }

    @Test
    void testAgregarYEliminarReserva() {
        Reserva reserva = new Reserva(cliente, clase, LocalDateTime.now(), Reserva.MetodoPago.EFECTIVO);
        cliente.agregarReserva(reserva);
        assertEquals(1, cliente.getHistorialReservas().size());

        cliente.eliminarReserva(reserva);
        assertEquals(0, cliente.getHistorialReservas().size());
    }

    @Test
    void testAgregarYEliminarClasesSeleccionadas() {
        cliente.agregarClaseSeleccionada(clase);
        assertEquals(1, cliente.getClasesSeleccionadas().size());

        cliente.eliminarClasesSeleccionadas();
        assertEquals(0, cliente.getClasesSeleccionadas().size());
    }

    @Test
    void testConfirmarClasesSeleccionadas() throws Exception {
        cliente.agregarClaseSeleccionada(clase);
        cliente.confirmarClasesSeleccionadas(sistema, Reserva.MetodoPago.TARJETA_DEBITO);

        assertEquals(2, cliente.getHistorialReservas().size());
        assertEquals(0, cliente.getClasesSeleccionadas().size());
    }
}