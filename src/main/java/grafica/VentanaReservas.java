package grafica;

import logica.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaReservas extends JFrame {

    private SistemaReserva sistemaReserva;
    private Cliente clienteActual;
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private JButton btnCancelarReserva;

    private void inicializarComponentes() {
        String[] columnas = {"Prueba", "Profesor", "Fecha y Hora Clase", "Fecha Reserva", "Método de Pago", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tablaReservas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);

        btnCancelarReserva = new JButton("Cancelar Reserva");

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnCancelarReserva);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }
}
