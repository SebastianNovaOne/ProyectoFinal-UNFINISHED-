package grafica;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal del sistema de reservas de clases particulares.
 * Esta clase permite al usuario entrar como cliente o profesor, o salir de la aplicacion.
 * Dependiendo de la seleccion, se abren diferentes ventanas para gestionar las reservas y clases.
 */
public class VentanaPrincipal extends JFrame {

    private JButton btnCliente;
    private JButton btnProfesor;
    private JButton btnSalir;

    private JButton btnGestionarClientes;
    private JButton btnGestionarClases;
    private JButton btnGestionarReservas;
    private JButton btnVolver;

    /**
     * Constructor de la ventana principal.
     * Configura la interfaz de la ventana principal con botones para cliente, profesor y salir.
     */
    public VentanaPrincipal() {
        setTitle("Sistema de Reservas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnCliente = new JButton("Entrar como Cliente");
        btnProfesor = new JButton("Entrar como Profesor");
        btnSalir = new JButton("Salir");

        btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarOpcionesCliente();
            }
        });

        btnProfesor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaAgregarClase().setVisible(true);
                dispose();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(new JLabel("Bienvenido al sistema de reservas de clases particulares!!!", SwingConstants.CENTER));
        panel.add(btnCliente);
        panel.add(btnProfesor);
        panel.add(btnSalir);

        add(panel);
    }

    /**
     * Muestra las opciones disponibles para un cliente.
     * Permite al cliente registrarse, buscar clases o ver sus reservas.
     */
    private void mostrarOpcionesCliente() {
        JPanel panelCliente = new JPanel(new GridLayout(5, 1, 10, 10));
        panelCliente.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnGestionarClientes = new JButton("Registrarse como Cliente");
        btnGestionarClases = new JButton("Buscar Clases");
        btnGestionarReservas = new JButton("Ver Mis Reservas");
        btnVolver = new JButton("Volver");

        btnGestionarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaClientes().setVisible(true);
                dispose();
            }
        });

        btnGestionarClases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente clienteSeleccionado = seleccionarClienteDialogo();
                if (clienteSeleccionado != null) {
                    new VentanaClases(clienteSeleccionado).setVisible(true);
                    dispose();
                }
            }
        });

        btnGestionarReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente clienteSeleccionado = seleccionarClienteDialogo();
                if (clienteSeleccionado != null) {
                    new VentanaReservas(clienteSeleccionado).setVisible(true);
                    dispose();
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal().setVisible(true);
            }
        });

        getContentPane().removeAll();
        panelCliente.add(new JLabel("Interfaz de Cliente", SwingConstants.CENTER));
        panelCliente.add(btnGestionarClientes);
        panelCliente.add(btnGestionarClases);
        panelCliente.add(btnGestionarReservas);
        panelCliente.add(btnVolver);

        add(panelCliente);
        revalidate();
        repaint();
    }

    /**
     * Muestra un cuadro de dialogo para seleccionar un cliente de la lista de clientes registrados.
     *
     * @return El cliente seleccionado o null si no hay clientes registrados.
     */
    private Cliente seleccionarClienteDialogo() {
        SistemaReserva sistema = SistemaReserva.getInstancia();
        java.util.List<Cliente> clientes = sistema.getClientes();

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Cliente[] clientesArray = clientes.toArray(new Cliente[0]);
        Cliente seleccionado = (Cliente) JOptionPane.showInputDialog(
                this,
                "Seleccione un cliente:",
                "Seleccionar Cliente",
                JOptionPane.PLAIN_MESSAGE,
                null,
                clientesArray,
                clientesArray[0]);

        return seleccionado;
    }

    /**
     * Metodo principal que inicia la aplicacion y muestra la ventana principal.
     *
     * @param args Arguments de la linea de comandos
     */
    public static void main(String[] args) {
        new VentanaPrincipal().setVisible(true);
    }
}