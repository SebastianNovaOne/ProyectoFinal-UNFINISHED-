import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.*;

import java.time.LocalDateTime;

public class ValidadorCupoTest {

    private ValidadorCupo validador;
    private Cliente cliente;
    private Profesor profesor;
    private Prueba prueba;
    private ClaseParticular claseConCupo;
    private ClaseParticular claseSinCupo;

    @BeforeEach
    void setUp() {
        validador = new ValidadorCupo();
        cliente = new Cliente("Jose Pedro", "jose.pedro@outlook.com");
        prueba = Prueba.MATEMATICA_M2;
        profesor = new Profesor("Profesor", prueba);

        claseConCupo = new ClaseParticular(prueba, profesor, LocalDateTime.now(), 60, 1);
        claseSinCupo = new ClaseParticular(prueba, profesor, LocalDateTime.now(), 60, 1);
        claseSinCupo.reservarCupo();
    }

    @Test
    void validar_ClaseConCupo_RetornaTrue() throws CupoExcedidoException {
        Reserva reserva = new Reserva(cliente, claseConCupo, LocalDateTime.now(), Reserva.MetodoPago.EFECTIVO);
        assertTrue(validador.validar(reserva));
    }

    @Test
    void validar_ClaseSinCupo_LanzaCupoExcedidoException() {
        Reserva reserva = new Reserva(cliente, claseSinCupo, LocalDateTime.now(), Reserva.MetodoPago.EFECTIVO);
        try {
            validador.validar(reserva);
            fail("Se esperaba CupoExcedidoException");
        } catch (CupoExcedidoException e) {
            assertEquals("No hay cupo disponible para esta clase.", e.getMessage());
        }
    }
}