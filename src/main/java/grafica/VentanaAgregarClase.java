package grafica;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaAgregarClase extends JFrame {

    private SistemaReserva sistemaReserva;
    private JComboBox<Prueba> comboTipoPrueba;
    private JComboBox<Profesor> comboProfesor;
    private JTextField txtFechaHora;
    private JTextField txtDuracion;
    private JTextField txtCupos;
    private JButton btnAgregarClase;
    private JButton btnGestionarProfesores;
    private JButton btnVolver;

    private void agregarClase() {
        try {
            Prueba tipoPrueba = (Prueba) comboTipoPrueba.getSelectedItem();
            Profesor profesor = (Profesor) comboProfesor.getSelectedItem();
            String fechaHoraStr = txtFechaHora.getText().trim();
            String duracionStr = txtDuracion.getText().trim();
            String cuposStr = txtCupos.getText().trim();

            if (fechaHoraStr.isEmpty() || duracionStr.isEmpty() || cuposStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
            int duracion = Integer.parseInt(duracionStr);
            int cupos = Integer.parseInt(cuposStr);

            ClaseParticular clase = new ClaseParticular(tipoPrueba, profesor, fechaHora, duracion, cupos);

            sistemaReserva.agregarClase(clase);

            JOptionPane.showMessageDialog(this, "Clase agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            txtFechaHora.setText("");
            txtDuracion.setText("");
            txtCupos.setText("");
            comboTipoPrueba.setSelectedIndex(0);
            comboProfesor.setSelectedIndex(0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar la clase: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}