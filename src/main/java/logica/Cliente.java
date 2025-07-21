package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un cliente en el sistema de reservas.
 * Un cliente tiene un nombre, un contacto, un historial de reservas y una lista de clases seleccionadas.
 */
public class Cliente {

    private String nombre;
    private String contacto;
    private List<Reserva> historialReservas;
    private List<ClaseParticular> clasesSeleccionadas;

    /**
     * Constructor para inicializar un cliente con su nombre y contacto.
     * @param nombre Nombre del cliente.
     * @param contacto Informacion de contacto del cliente.
     */
    public Cliente(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.historialReservas = new ArrayList<>();
        this.clasesSeleccionadas = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la informacion de contacto del cliente.
     * @return El contacto del cliente.
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * Obtiene el historial de reservas del cliente.
     * @return Una lista con las reservas del cliente.
     */
    public List<Reserva> getHistorialReservas() {
        return historialReservas;
    }

    /**
     * Obtiene la lista de clases seleccionadas por el cliente.
     * @return Una lista de clases seleccionadas.
     */
    public List<ClaseParticular> getClasesSeleccionadas() {
        return clasesSeleccionadas;
    }

    /**
     * Agrega una reserva al historial de reservas del cliente.
     * @param reserva La reserva que se quiere agregar.
     */
    public void agregarReserva(Reserva reserva) {
        historialReservas.add(reserva);
    }

    /**
     * Elimina una reserva del historial de reservas del cliente.
     * @param reserva La reserva que se quiere eliminar.
     */
    public void eliminarReserva(Reserva reserva) {
        historialReservas.remove(reserva);
    }

    /**
     * Agrega una clase seleccionada por el cliente a la lista de clases seleccionadas.
     * @param clase La clase que el cliente ha seleccionado.
     */
    public void agregarClaseSeleccionada(ClaseParticular clase) {
        clasesSeleccionadas.add(clase);
    }

    /**
     * Elimina todas las clases seleccionadas por el cliente.
     */
    public void eliminarClasesSeleccionadas() {
        clasesSeleccionadas.clear();
    }

    /**
     * Confirma las clases seleccionadas por el cliente creando reservas para cada una
     * y agregandolas al historial de reservas del cliente. Despues limpia la lista de clases seleccionadas.
     * @param sistemaReserva El sistema de reservas que gestionara las reservas.
     * @param metodoPago El metodo de pago utilizado para las reservas.
     * @throws Exception Si ocurre un error al crear las reservas.
     */
    public void confirmarClasesSeleccionadas(SistemaReserva sistemaReserva, Reserva.MetodoPago metodoPago) throws Exception {
        for (ClaseParticular clase : clasesSeleccionadas) {
            Reserva reserva = sistemaReserva.crearReserva(this, clase, metodoPago);
            agregarReserva(reserva);
        }
        eliminarClasesSeleccionadas();
    }

    @Override
    public String toString() {
        return nombre;
    }
}