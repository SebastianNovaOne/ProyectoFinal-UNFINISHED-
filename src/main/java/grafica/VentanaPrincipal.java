package grafica;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private JButton btnCliente;
    private JButton btnProfesor;
    private JButton btnSalir;

    private JButton btnGestionarClientes;
    private JButton btnGestionarClases;
    private JButton btnGestionarReservas;
    private JButton btnSalirCliente;
    private JButton btnVolver;

    public VentanaPrincipal() {
        setTitle("Sistema de Reservas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
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

        panel.add(new JLabel("Bienvenido al sistema de reservas", SwingConstants.CENTER));
        panel.add(btnCliente);
        panel.add(btnProfesor);
        panel.add(btnSalir);

        add(panel);
    }

    private void mostrarOpcionesCliente() {
        JPanel panelCliente = new JPanel(new GridLayout(5, 1, 10, 10));
        panelCliente.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnGestionarClientes = new JButton("Añadir Clientes");
        btnGestionarClases = new JButton("Gestionar Clases");
        btnGestionarReservas = new JButton("Gestionar Reservas");
        btnSalirCliente = new JButton("Salir");
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

        btnSalirCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal().setVisible(true);
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
        panelCliente.add(new JLabel("Opciones de Cliente", SwingConstants.CENTER));
        panelCliente.add(btnGestionarClientes);
        panelCliente.add(btnGestionarClases);
        panelCliente.add(btnGestionarReservas);
        panelCliente.add(btnSalirCliente);
        panelCliente.add(btnVolver);

        add(panelCliente);
        revalidate();
        repaint();
    }
}