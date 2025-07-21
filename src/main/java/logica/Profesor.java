package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al profesor en el sistema.
 * El profesor tiene un nombre, una especialidad y una lista de horarios disponibles
 * para impartir clases.
 */
public class Profesor {
    private String nombre;
    private Prueba especialidad;
    private List<String> horariosDisponibles;

    /**
     * Constructor de la clase Profesor.
     *
     * @param nombre El nombre del profesor.
     * @param especialidad La especialidad.
     */
    public Profesor(String nombre, Prueba especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.horariosDisponibles = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del profesor.
     *
     * @return El nombre del profesor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la especialidad del profesor.
     *
     * @return La especialidad del profesor.
     */
    public Prueba getEspecialidad() {
        return especialidad;
    }

    /**
     * Obtiene la lista de horarios disponibles del profesor.
     *
     * @return Una lista de strings que representan los horarios disponibles.
     */
    public List<String> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    @Override
    public String toString() {
        return nombre;
    }
}