package grafica;

import logica.Cliente;
import logica.SistemaReserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaClientes extends JFrame {

    private JTextField nombreField;
    private JTextField contactoField;
    private DefaultListModel<Cliente> modeloListaClientes;
    private JList<Cliente> listaClientes;
    private JButton btnVolver;

    public VentanaClientes() {
        setTitle("Gestión de Clientes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(3, 2, 5, 5));
        formulario.setBorder(BorderFactory.createTitledBorder("Agregar Cliente"));

        formulario.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formulario.add(nombreField);

        formulario.add(new JLabel("Contacto:"));
        contactoField = new JTextField();
        formulario.add(contactoField);

        JButton agregarBtn = new JButton("Agregar");
        formulario.add(agregarBtn);

        panel.add(formulario, BorderLayout.NORTH);

        modeloListaClientes = new DefaultListModel<>();
        listaClientes = new JList<>(modeloListaClientes);
        JScrollPane scrollPane = new JScrollPane(listaClientes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Clientes registrados"));
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        agregarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });
    }
}