package logica;

import java.time.LocalDateTime;

public class ClaseParticular {
    private Prueba tipoPrueba;
    private Profesor profesor;
    private LocalDateTime horario;
    private LocalDateTime fechaHora;
    private int duracion;
    private int cuposTotales;
    private int cuposReservados;

    public ClaseParticular(Prueba tipoPrueba, Profesor profesor, LocalDateTime fechaHora, int duracion, int cuposTotales) {
        this.tipoPrueba = tipoPrueba;
        this.profesor = profesor;
        this.horario = horario;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.cuposTotales = cuposTotales;
        this.cuposReservados = 0;
    }
}