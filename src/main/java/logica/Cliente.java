package logica;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String contacto;
    private List<Reserva> historialReservas;
    private List<ClaseParticular> clasesSeleccionadas;

    public Cliente(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.historialReservas = new ArrayList<>();
        this.clasesSeleccionadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public List<Reserva> getHistorialReservas() {
        return historialReservas;
    }

    public List<ClaseParticular> getClasesSeleccionadas() {
        return clasesSeleccionadas;
    }
}