package logica;

import java.util.List;

public class Pregunta {
    private String enunciado;
    private List<String> opciones;

    public Pregunta(String enunciado, List<String> opciones) {
        this.enunciado = enunciado;
        this.opciones = opciones;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public List<String> getOpciones() {
        return opciones;
    }
}