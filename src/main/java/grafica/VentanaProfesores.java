package grafica;

import logica.*;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana que permite gestionar los profesores en el sistema.
 * Permite agregar, eliminar y ver detalles de los profesores registrados.
 * Esta ventana es utilizada por la ventana de agregar clase para seleccionar un profesor para una clase.
 */
public class VentanaProfesores extends JFrame {

    private SistemaReserva sistema;
    private DefaultListModel<Profesor> modeloProfesores;
    private JList<Profesor> listaProfesores;
    private VentanaAgregarClase ventanaAgregarClase;

    /**
     * Constructor de la ventana de gestión de profesores.
     * Inicializa la interfaz grafica con los componentes necesarios para mostrar la lista de profesores
     * y los botones para agregar, eliminar y ver detalles de los profesores.
     *
     * @param ventanaAgregarClase La ventana que llama a esta ventana para actualizar su combo de profesores.
     */
    public VentanaProfesores(VentanaAgregarClase ventanaAgregarClase) {
        this.ventanaAgregarClase = ventanaAgregarClase;
        sistema = SistemaReserva.getInstancia();
        setTitle("Gestión de Profesores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloProfesores = new DefaultListModel<>();
        listaProfesores = new JList<>(modeloProfesores);
        listaProfesores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProfesores.setCellRenderer(new ProfesorCellRenderer());

        JScrollPane scroll = new JScrollPane(listaProfesores);

        JButton btnAgregar = new JButton("Agregar Profesor");
        JButton btnEliminar = new JButton("Remover Profesor");
        JButton btnDetalles = new JButton("Ver Detalles");

        btnAgregar.addActionListener(e -> mostrarDialogo());
        btnEliminar.addActionListener(e -> removerProfesor());
        btnDetalles.addActionListener(e -> mostrarDetalles());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnDetalles);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Muestra un cuadro de dialogo para ingresar los datos de un nuevo profesor.
     * Los datos incluyen el nombre del profesor y su especialidad (uno de los valores del enum Prueba).
     * Si los datos son validos, se agrega el nuevo profesor al sistema.
     */
    private void mostrarDialogo() {
        JTextField nombreField = new JTextField();
        JTextField especialidadField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Especialidad (nombre exacto del enum Prueba):"));
        panel.add(especialidadField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Profesor",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText().trim();
            String especialidadNombre = especialidadField.getText().trim();

            if (nombre.isEmpty() || especialidadNombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Prueba especialidad;
            try {
                especialidad = Prueba.valueOf(especialidadNombre.toUpperCase());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Especialidad no válida. Debe ser uno de los valores de Prueba.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Profesor nuevo = new Profesor(nombre, especialidad);
            sistema.agregarProfesor(nuevo);
            modeloProfesores.addElement(nuevo);

            // Actualiza el combo de profesores en la ventana de agregar clase
            ventanaAgregarClase.actualizarComboProfesores();
        }
    }

    /**
     * Remueve el profesor seleccionado de la lista de profesores.
     * Si no se selecciona un profesor, se muestra un mensaje de advertencia.
     */
    private void removerProfesor() {
        Profesor seleccionado = listaProfesores.getSelectedValue();
        if (seleccionado != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Remover profesor " + seleccionado.getNombre() + "?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                sistema.getProfesores().remove(seleccionado);
                modeloProfesores.removeElement(seleccionado);

                // Actualiza el combo de profesores en la ventana de agregar clase
                ventanaAgregarClase.actualizarComboProfesores();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un profesor para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Muestra los detalles del profesor seleccionado, como su nombre, especialidad y horarios disponibles.
     * Si no se selecciona un profesor, se muestra un mensaje de advertencia.
     */
    private void mostrarDetalles() {
        Profesor seleccionado = listaProfesores.getSelectedValue();
        if (seleccionado != null) {
            StringBuilder detalles = new StringBuilder();
            detalles.append("Nombre: ").append(seleccionado.getNombre()).append("\n");
            detalles.append("Especialidad: ").append(seleccionado.getEspecialidad().name()).append("\n");
            detalles.append("Horarios disponibles: ").append(String.join(", ", seleccionado.getHorariosDisponibles()));

            JOptionPane.showMessageDialog(this, detalles.toString(), "Detalles del Profesor", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un profesor para ver detalles.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Clase interna que personaliza la forma en que se renderiza cada elemento en la lista de profesores.
     * Muestra el nombre del profesor en cada celda de la lista.
     */
    private static class ProfesorCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Profesor) {
                Profesor prof = (Profesor) value;
                setText(prof.getNombre());
            }
            return this;
        }
    }
}