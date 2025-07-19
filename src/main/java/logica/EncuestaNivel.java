package logica;

import java.util.List;

public class EncuestaNivel {
    private List<Pregunta> preguntas;

    public EncuestaNivel(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public List<Pregunta> obtenerPreguntas() {
        return preguntas;
    }
}