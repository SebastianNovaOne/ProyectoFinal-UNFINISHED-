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

    public void agregarReserva(Reserva reserva) {
        historialReservas.add(reserva);
    }

    public void eliminarReserva(Reserva reserva) {
        historialReservas.remove(reserva);
    }


    public void agregarClaseSeleccionada(ClaseParticular clase) {
        clasesSeleccionadas.add(clase);
    }


    public void eliminarClasesSeleccionadas() {
        clasesSeleccionadas.clear();
    }

    public void confirmarClasesSeleccionadas(SistemaReserva sistemaReserva, Reserva.MetodoPago metodoPago) throws Exception {
        for (ClaseParticular clase : clasesSeleccionadas) {
            Reserva reserva = sistemaReserva.crearReserva(this, clase, metodoPago);
            agregarReserva(reserva);
        }
        eliminarClasesSeleccionadas();
    }
}