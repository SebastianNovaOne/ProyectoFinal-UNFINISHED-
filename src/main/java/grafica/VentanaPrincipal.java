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
}