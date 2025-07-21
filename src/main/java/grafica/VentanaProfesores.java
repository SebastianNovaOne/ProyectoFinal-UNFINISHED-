package grafica;

import logica.*;
import javax.swing.*;
import java.awt.*;

public class VentanaProfesores extends JFrame {

    private SistemaReserva sistema;
    private DefaultListModel<Profesor> modeloProfesores;
    private JList<Profesor> listaProfesores;
    private VentanaAgregarClase ventanaAgregarClase;

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
        JButton btnEliminar = new JButton("Eliminar Profesor");
        JButton btnDetalles = new JButton("Ver Detalles");

        btnAgregar.addActionListener(e -> mostrarDialogo());
        btnEliminar.addActionListener(e -> eliminarProfesor());
        btnDetalles.addActionListener(e -> mostrarDetalles());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnDetalles);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

    }

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

            ventanaAgregarClase.actualizarComboProfesores();
        }
    }

    private void eliminarProfesor() {
        Profesor seleccionado = listaProfesores.getSelectedValue();
        if (seleccionado != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar profesor " + seleccionado.getNombre() + "?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                sistema.getProfesores().remove(seleccionado);
                modeloProfesores.removeElement(seleccionado);
                ventanaAgregarClase.actualizarComboProfesores();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un profesor para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

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
