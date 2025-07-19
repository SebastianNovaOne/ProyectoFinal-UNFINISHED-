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

    public String getNombre() {
        return nombre;
    }

    public Prueba getEspecialidad() {
        return especialidad;
    }

    public List<String> getHorariosDisponibles() {
        return horariosDisponibles;
    }
}

