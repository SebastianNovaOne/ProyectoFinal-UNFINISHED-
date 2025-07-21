package logica;

import java.time.LocalDateTime;

/**
 * Clase que representa una clase particular dentro del sistema de reservas.
 * Cada clase particular tiene un tipo de prueba, un profesor asignado, una fecha y hora,
 * duracion, cupos totales y cupos reservados.
 */
public class ClaseParticular {

    private Prueba tipoPrueba;
    private Profesor profesor;
    private LocalDateTime horario;
    private LocalDateTime fechaHora;
    private int duracion;
    private int cuposTotales;
    private int cuposReservados;

    /**
     * Constructor para inicializar una clase particular con la informacion basica.
     * @param tipoPrueba Tipo de prueba que se enseña en la clase.
     * @param profesor Profesor que enseña la clase.
     * @param fechaHora Fecha y hora en que se lleva a cabo la clase.
     * @param duracion Duracion de la clase en minutos.
     * @param cuposTotales Numero total de cupos disponibles para la clase.
     */
    public ClaseParticular(Prueba tipoPrueba, Profesor profesor, LocalDateTime fechaHora, int duracion, int cuposTotales) {
        this.tipoPrueba = tipoPrueba;
        this.profesor = profesor;
        this.horario = horario;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.cuposTotales = cuposTotales;
        this.cuposReservados = 0;
    }

    /**
     * Obtiene el tipo de prueba de la clase.
     * @return El tipo de prueba.
     */
    public Prueba getTipoPrueba() {
        return tipoPrueba;
    }

    /**
     * Obtiene el profesor asignado a la clase.
     * @return El profesor que imparte la clase.
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Obtiene el horario de la clase.
     * @return La fecha y hora programada para la clase.
     */
    public LocalDateTime getHorario() {
        return horario;
    }

    /**
     * Obtiene la duracion de la clase en minutos.
     * @return La duracion de la clase en minutos.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Obtiene el numero de cupos disponibles para la clase.
     * @return El numero de cupos disponibles.
     */
    public int getCuposDisponibles() {
        return cuposTotales - cuposReservados;
    }

    /**
     * Obtiene la fecha y hora de la clase.
     * @return La fecha y hora de la clase.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Reserva un cupo en la clase si hay disponibilidad.
     * @return True si el cupo fue reservado exitosamente, false si no hay cupos disponibles.
     */
    public boolean reservarCupo() {
        if (cuposReservados < cuposTotales) {
            cuposReservados++;
            return true;
        }
        return false;
    }

    /**
     * Libera un cupo de la clase si hay cupos reservados.
     * @return True si el cupo fue liberado exitosamente, false si no hay cupos reservados.
     */
    public boolean liberarCupo() {
        if (cuposReservados > 0) {
            cuposReservados--;
            return true;
        }
        return false;
    }
}