package logica;

import java.util.List;

/**
 * La clase {@code EncuestaNivel} representa una encuesta compuesta por un set de preguntas.
 * Proporciona un mecanismo para obtener las preguntas que componen la encuesta.
 */
public class EncuestaNivel {

    /**
     * Lista de preguntas que forman la encuesta.
     */
    private List<Pregunta> preguntas;

    /**
     * Constructor que inicia una nueva encuesta con una lista de preguntas.
     *
     * @param preguntas La lista de preguntas que forman la encuesta.
     */
    public EncuestaNivel(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * Obtiene la lista de preguntas que conforman la encuesta.
     *
     * @return Una lista de objetos {@link Pregunta}.
     */
    public List<Pregunta> obtenerPreguntas() {
        return preguntas;
    }
}