package grafica;

import logica.Cliente;
import logica.SistemaReserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana para gestionar clientes.
 * Permite agregar nuevos clientes y visualizar la lista de clientes registrados en el sistema.
 */
public class VentanaClientes extends JFrame {

    private JTextField nombreField;
    private JTextField contactoField;
    private DefaultListModel<Cliente> modeloListaClientes;
    private JList<Cliente> listaClientes;
    private JButton btnVolver;

    /**
     * Constructor de la ventana de gestion de clientes.
     * Inicializa los componentes de la ventana, incluyendo el formulario para agregar clientes y la lista de clientes registrados.
     */
    public VentanaClientes() {
        setTitle("Gestion de Clientes");
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

        cargarClientes();

        agregarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal().setVisible(true);
            }
        });
        panel.add(btnVolver, BorderLayout.SOUTH);
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * Toma los valores de los campos de texto, valida que no estén vacíos y luego agrega el cliente al sistema.
     */
    private void registrarCliente() {
        String nombre = nombreField.getText().trim();
        String contacto = contactoField.getText().trim();

        if (!nombre.isEmpty() && !contacto.isEmpty()) {
            Cliente cliente = new Cliente(nombre, contacto);
            SistemaReserva.getInstancia().agregarCliente(cliente);
            modeloListaClientes.addElement(cliente);
            nombreField.setText("");
            contactoField.setText("");
        } else {
            JOptionPane.showMessageDialog(VentanaClientes.this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga los clientes registrados en el sistema y los muestra en la lista.
     */
    private void cargarClientes() {
        for (Cliente c : SistemaReserva.getInstancia().getClientes()) {
            modeloListaClientes.addElement(c);
        }
    }
}