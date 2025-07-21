package logica;

import java.util.List;

/**
 * Representa una pregunta con un enunciado y un set de opciones.
 */
public class Pregunta {

    private String enunciado;
    private List<String> opciones;

    /**
     * Constructor que crea nueva pregunta con enunciado y las opciones proporcionadas.
     *
     * @param enunciado El enunciado de la pregunta.
     * @param opciones Las opciones de respuesta disponibles para la pregunta.
     */
    public Pregunta(String enunciado, List<String> opciones) {
        this.enunciado = enunciado;
        this.opciones = opciones;
    }

    /**
     * Obtiene el enunciado de la pregunta.
     *
     * @return El enunciado de la pregunta.
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Obtiene la lista de opciones disponibles para la pregunta.
     *
     * @return La lista de opciones.
     */
    public List<String> getOpciones() {
        return opciones;
    }
}