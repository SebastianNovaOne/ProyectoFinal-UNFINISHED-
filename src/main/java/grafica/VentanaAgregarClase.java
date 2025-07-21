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

    public VentanaAgregarClase() {
        sistemaReserva = SistemaReserva.getInstancia();

        setTitle("Agregar Clase");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(9, 2, 10, 10));

        add(new JLabel("Tipo de Prueba:"));
        comboTipoPrueba = new JComboBox<>(Prueba.values());
        add(comboTipoPrueba);

        add(new JLabel("Profesor:"));
        comboProfesor = new JComboBox<>();
        cargarProfesores();
        add(comboProfesor);

        add(new JLabel("Fecha y Hora (yyyy-MM-dd HH:mm) (EJEMPLO: 2025-10-10 17:00):"));
        txtFechaHora = new JTextField();
        add(txtFechaHora);

        add(new JLabel("Duración (min):"));
        txtDuracion = new JTextField();
        add(txtDuracion);

        add(new JLabel("Cupos Disponibles:"));
        txtCupos = new JTextField();
        add(txtCupos);

        btnAgregarClase = new JButton("Agregar Clase");
        btnAgregarClase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarClase();
            }
        });
        add(new JLabel(""));
        add(btnAgregarClase);

        btnGestionarProfesores = new JButton("Añadir Profesores");
        btnGestionarProfesores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaProfesores(VentanaAgregarClase.this).setVisible(true);
            }
        });
        add(new JLabel(""));
        add(btnGestionarProfesores);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal().setVisible(true);
            }
        });
        add(new JLabel(""));
        add(btnVolver);

        setVisible(true);
    }

    private void cargarProfesores() {
        List<Profesor> profesores = sistemaReserva.getProfesores();
        for (Profesor profesor : profesores) {
            comboProfesor.addItem(profesor);
        }
    }

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

    public void actualizarComboProfesores() {
        comboProfesor.removeAllItems();
        cargarProfesores();
    }
}