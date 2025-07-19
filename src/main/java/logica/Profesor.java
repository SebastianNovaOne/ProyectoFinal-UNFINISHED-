package logica;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String nombre;
    private Prueba especialidad;
    private List<String> horariosDisponibles;

    public Profesor(String nombre, Prueba especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.horariosDisponibles = new ArrayList<>();
    }
}

