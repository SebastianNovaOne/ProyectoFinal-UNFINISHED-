package grafica;

import logica.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Ventana para gestionar y visualizar las reservas de un cliente.
 * Permite ver las reservas actuales, cancelarlas y muestra los detalles
 * de cada reserva realizada por el cliente.
 */
public class VentanaReservas extends JFrame {

    private SistemaReserva sistemaReserva;
    private Cliente clienteActual;
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private JButton btnCancelarReserva;

    /**
     * Constructor de la ventana de reservas.
     *
     * @param cliente El cliente cuyas reservas seran mostradas.
     */
    public VentanaReservas(Cliente cliente) {
        super("Reservas de " + cliente.getNombre());
        this.clienteActual = cliente;
        sistemaReserva = SistemaReserva.getInstancia();

        inicializarComponentes();
        cargarReservas();
        configurarEventos();

        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Inicia los componentes de la ventana (tabla y botones).
     */
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

    /**
     * Carga las reservas del cliente en la tabla.
     */
    private void cargarReservas() {
        modeloTabla.setRowCount(0);
        List<Reserva> reservas = sistemaReserva.getReservasPorCliente(clienteActual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Reserva reserva : reservas) {
            ClaseParticular clase = reserva.getClase();
            Object[] fila = {
                    clase.getTipoPrueba(),
                    clase.getProfesor().getNombre(),
                    clase.getFechaHora().format(formatter),
                    reserva.getFechaReserva().format(formatter),
                    reserva.getMetodoPago(),
                    reserva.getEstado()
            };
            modeloTabla.addRow(fila);
        }
    }

    /**
     * Configura los eventos asociados a los botones en la ventana.
     */
    private void configurarEventos() {
        btnCancelarReserva.addActionListener(e -> {
            int filaSeleccionada = tablaReservas.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una reserva para cancelar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Reserva reservaSeleccionada = sistemaReserva.getReservasPorCliente(clienteActual).get(filaSeleccionada);

            if (reservaSeleccionada.getEstado() == Reserva.Estado.CANCELADA) {
                JOptionPane.showMessageDialog(this, "La reserva ya está cancelada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea cancelar esta reserva?", "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    sistemaReserva.cancelarReserva(reservaSeleccionada);
                    JOptionPane.showMessageDialog(this, "Reserva cancelada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarReservas();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al cancelar la reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}