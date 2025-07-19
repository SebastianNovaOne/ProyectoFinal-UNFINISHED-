package logica;

import java.util.ArrayList;
import java.util.List;

public class GeneradorEncuesta {

    public static List<Pregunta> obtenerPreguntasPara(Prueba prueba) {
        List<Pregunta> preguntas = new ArrayList<>();

        switch (prueba) {
            case MATEMATICA_M1 -> {
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Aritmetica", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Algebra y Funciones", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Geometria", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Probabilidad y Estadistica", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));

            }
            case MATEMATICA_M2 -> {
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Aritmetica M2", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Algebra y Funciones M2", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Geometria M2", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Probabilidad y Estadistica M2", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
            }
            case COMPETENCIA_LECTORA -> {
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Localizacion de Informacion ", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Capacidad de Interpretacion e Inferencia", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Capacidad de Evaluacion y Analisis", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
            }
            case CIENCIAS_FISICA -> {
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Ondas", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Mecanica", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Energia y Tierra", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Electricidad", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
            }
            case CIENCIAS_QUIMICA -> {
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Estructura Atomica", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Quimica Organica", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Reacciones Quimicas y Estequiometria", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
            }
            case CIENCIAS_BIOLOGIA -> {
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Organizacion, estructura y actividad celular.", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Procesos y funciones biologicas", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Herencia y Evolucion", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Organismo y ambiente", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
            }
            case HISTORIA_Y_CIENCIAS_SOCIALES -> {
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Historia de Chile, America y Europa del Siglo XIX", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Formacion Ciudadana", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
                preguntas.add(new Pregunta("Marca tu nivel de conocimiento en: Sistemas Economicos", List.of("1 - Conocimiento Nulo", "2 - Conocimiento Bajo", "3 - Conocimiento Regular", "4 - Conocimiento Avanzado", "5 - Conocimiento Completo")));
            }
        }

        return preguntas;
    }
}