package grafica;

import logica.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;

/**
 * Ventana que muestra las clases disponibles para que un cliente las reserve.
 * Permite al cliente seleccionar una clase, elegir el metodo de pago y realizar una reserva.
 * Al realizar la reserva, se muestra una encuesta para evaluar la preparacion de la clase.
 */
public class VentanaClases extends JFrame {

    private SistemaReserva sistemaReserva;
    private JTable tablaClases;
    private DefaultTableModel modeloTabla;
    private JButton btnReservar;
    private JComboBox<Reserva.MetodoPago> comboMetodoPago;
    private Cliente clienteActual;
    private JButton btnVolver;

    /**
     * Constructor de la ventana de clases disponibles.
     * Inicia los componentes de la ventana, carga las clases y establece los eventos.
     * @param cliente El cliente que esta visualizando las clases disponibles.
     */
    public VentanaClases(Cliente cliente) {
        super("Clases Disponibles");
        this.clienteActual = cliente;
        sistemaReserva = SistemaReserva.getInstancia();
        inicializarComponentes();
        cargarClases();
        setEventos();
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Inicializa los componentes de la interfaz grafica.
     * Crea la tabla para mostrar las clases disponibles, los botones para realizar la reserva
     * y la selección del metodo de pago.
     */
    private void inicializarComponentes() {
        String[] columnas = {"Prueba", "Profesor", "Fecha y Hora", "Duración (min)", "Cupos Disponibles"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaClases = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClases);

        btnReservar = new JButton("Reservar Clase");
        comboMetodoPago = new JComboBox<>(Reserva.MetodoPago.values());

        btnVolver = new JButton("Volver");

        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Método de Pago:"));
        panelInferior.add(comboMetodoPago);
        panelInferior.add(btnReservar);
        panelInferior.add(btnVolver);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Carga las clases disponibles en el sistema y las muestra en la tabla.
     * Cada fila muestra el tipo de prueba, el nombre del profesor, la fecha y hora de la clase,
     * la duración en minutos y los cupos disponibles.
     */
    private void cargarClases() {
        modeloTabla.setRowCount(0);
        List<ClaseParticular> clases = sistemaReserva.getClases();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (ClaseParticular clase : clases) {
            Object[] fila = {
                    clase.getTipoPrueba(),
                    clase.getProfesor().getNombre(),
                    clase.getFechaHora().format(formatter),
                    clase.getDuracion(),
                    clase.getCuposDisponibles()
            };
            modeloTabla.addRow(fila);
        }
    }

    /**
     * Establece los eventos para los botones de la ventana.
     * Asocia las acciones de reserva de clase, como seleccionar una clase, elegir un metodo de pago
     * y crear una reserva.
     */
    private void setEventos() {
        btnReservar.addActionListener(e -> {
            int filaSeleccionada = tablaClases.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una clase para reservar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ClaseParticular claseSeleccionada = sistemaReserva.getClases().get(filaSeleccionada);
            Reserva.MetodoPago metodoPago = (Reserva.MetodoPago) comboMetodoPago.getSelectedItem();

            try {
                sistemaReserva.crearReserva(clienteActual, claseSeleccionada, metodoPago);
                JOptionPane.showMessageDialog(this, "Reserva creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                List<Pregunta> preguntas = GeneradorEncuesta.obtenerPreguntasPara(claseSeleccionada.getTipoPrueba());
                EncuestaNivel encuesta = new EncuestaNivel(preguntas);

                mostrarEncuesta(encuesta);

                cargarClases();

            } catch (CupoExcedidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Cupo", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al crear la reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaPrincipal().setVisible(true);
        });
    }

    /**
     * Muestra la ventana de encuesta de preparacion luego de realizar una reserva.
     * La encuesta esta formada por una serie de preguntas de opcion multiple,
     * y el usuario puede seleccionar sus respuestas.
     * @param encuesta La encuesta que se muestra al usuario.
     */
    private void mostrarEncuesta(EncuestaNivel encuesta) {
        JFrame ventanaEncuesta = new JFrame("Encuesta de Preparacion");
        ventanaEncuesta.setSize(900, 800);
        ventanaEncuesta.setLocationRelativeTo(this);
        ventanaEncuesta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelEncuesta = new JPanel();
        panelEncuesta.setLayout(new BoxLayout(panelEncuesta, BoxLayout.Y_AXIS));

        List<Pregunta> preguntas = encuesta.obtenerPreguntas();
        ButtonGroup[] gruposRespuestas = new ButtonGroup[preguntas.size()];

        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            JPanel panelPregunta = new JPanel();
            panelPregunta.setLayout(new BoxLayout(panelPregunta, BoxLayout.Y_AXIS));

            panelPregunta.add(new JLabel(pregunta.getEnunciado()));

            gruposRespuestas[i] = new ButtonGroup();

            for (String alternativa : pregunta.getOpciones()) {
                JRadioButton radioButton = new JRadioButton(alternativa);
                gruposRespuestas[i].add(radioButton);
                panelPregunta.add(radioButton);
            }

            panelEncuesta.add(panelPregunta);
        }

        JPanel panelBotones = new JPanel();
        JButton btnEnviarEncuesta = new JButton("Enviar Encuesta");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnEnviarEncuesta);
        panelBotones.add(btnCancelar);

        panelEncuesta.add(panelBotones);

        ventanaEncuesta.getContentPane().add(panelEncuesta);
        ventanaEncuesta.setVisible(true);

        btnEnviarEncuesta.addActionListener(e -> {
            for (int i = 0; i < preguntas.size(); i++) {
                ButtonGroup grupo = gruposRespuestas[i];
                for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons.hasMoreElements();) {
                    JRadioButton button = (JRadioButton) buttons.nextElement();
                    if (button.isSelected()) {
                        System.out.println("Respuesta para '" + preguntas.get(i).getEnunciado() + "': " + button.getText());
                    }
                }
            }
            JOptionPane.showMessageDialog(ventanaEncuesta, "Gracias por completar la encuesta.", "Encuesta Enviada", JOptionPane.INFORMATION_MESSAGE);
            ventanaEncuesta.dispose();
        });
        btnCancelar.addActionListener(e -> ventanaEncuesta.dispose());
    }
}