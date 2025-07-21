package grafica;

import logica.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaClases extends JFrame {

    private SistemaReserva sistemaReserva;
    private JTable tablaClases;
    private DefaultTableModel modeloTabla;
    private JButton btnReservar;
    private JComboBox<Reserva.MetodoPago> comboMetodoPago;
    private Cliente clienteActual;
    private JButton btnVolver;

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
}