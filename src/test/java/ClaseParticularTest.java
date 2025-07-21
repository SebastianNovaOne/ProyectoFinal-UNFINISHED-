import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import logica.*;

public class ClaseParticularTest {

    private ClaseParticular clase;
    private Profesor profesor;

    @BeforeEach
    void setup() {
        profesor = new Profesor("Pepe", Prueba.MATEMATICA_M1);
        clase = new ClaseParticular(Prueba.MATEMATICA_M1, profesor, LocalDateTime.now().plusDays(1), 60, 3);
    }

    @Test
    void testReservarCupoDisponible() {
        assertTrue(clase.reservarCupo());
        assertEquals(2, clase.getCuposDisponibles());
    }

    @Test
    void testReservarCupoAgotados() {
        clase.reservarCupo();
        clase.reservarCupo();
        clase.reservarCupo();
        assertFalse(clase.reservarCupo());
        assertEquals(0, clase.getCuposDisponibles());
    }

    @Test
    void testLiberarCupoConReservas() {
        clase.reservarCupo();
        assertTrue(clase.liberarCupo());
        assertEquals(3, clase.getCuposDisponibles());
    }

    @Test
    void testLiberarCupoSinReservas() {
        assertFalse(clase.liberarCupo());
        assertEquals(3, clase.getCuposDisponibles());
    }

    @Test
    void testGetCuposDisponibles() {
        assertEquals(3, clase.getCuposDisponibles());
        clase.reservarCupo();
        assertEquals(2, clase.getCuposDisponibles());
    }
}