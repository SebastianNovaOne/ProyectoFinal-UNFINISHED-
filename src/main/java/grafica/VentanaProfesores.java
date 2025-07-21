package grafica;

import logica.*;
import javax.swing.*;
import java.awt.*;

public class VentanaProfesores extends JFrame {

    private SistemaReserva sistema;
    private DefaultListModel<Profesor> modeloProfesores;
    private JList<Profesor> listaProfesores;
    private VentanaAgregarClase ventanaAgregarClase;

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
}
