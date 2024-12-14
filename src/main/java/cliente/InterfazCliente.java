// Clase que define la interfaz gráfica del cliente
package cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InterfazCliente extends JFrame {
    private final JTextArea areaMensajes; // Área para mostrar mensajes del chat
    private final JComboBox<String> usuariosConectados; // Lista desplegable de usuarios conectados
    private final JTextField campoTexto; // Campo para escribir mensajes
    private final ConexionCliente conexion; // Conexión con el servidor

    // Constructor que inicializa la interfaz gráfica
    public InterfazCliente(String servidor, int puerto, String usuario) throws Exception {
        setTitle("Chat de Usuario: " + usuario); // Título de la ventana
        setSize(700, 500); // Dimensiones de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Acción al cerrar la ventana

        // Configurar área de mensajes
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        areaMensajes.setBackground(new Color(40, 40, 40));
        areaMensajes.setForeground(Color.WHITE);
        areaMensajes.setFont(new Font("Courier New", Font.PLAIN, 14));
        JScrollPane scrollMensajes = new JScrollPane(areaMensajes);

        // Panel inferior con campo de texto y botón de enviar
        JPanel panelInferior = new JPanel(new BorderLayout());
        usuariosConectados = new JComboBox<>();
        usuariosConectados.setBackground(Color.WHITE);
        usuariosConectados.setFont(new Font("Verdana", Font.PLAIN, 14));
        usuariosConectados.setPreferredSize(new Dimension(200, 25));

        campoTexto = new JTextField();
        campoTexto.setFont(new Font("Verdana", Font.PLAIN, 14));

        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setFont(new Font("Verdana", Font.BOLD, 14));
        botonEnviar.setBackground(new Color(70, 130, 180));
        botonEnviar.setForeground(Color.WHITE);

        // Añadir componentes al panel inferior
        panelInferior.add(usuariosConectados, BorderLayout.WEST);
        panelInferior.add(campoTexto, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);

        // Añadir componentes principales a la ventana
        add(scrollMensajes, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Establecer conexión con el servidor
        conexion = new ConexionCliente(servidor, puerto, usuario, this);

        // Acción del botón de enviar
        botonEnviar.addActionListener((ActionEvent e) -> {
            String destinatario = (String) usuariosConectados.getSelectedItem(); // Usuario destino
            String mensaje = campoTexto.getText(); // Mensaje a enviar
            conexion.enviarMensaje(destinatario, mensaje); // Enviar mensaje al servidor
            areaMensajes.append("Yo a " + destinatario + ": " + mensaje + "\n"); // Mostrar mensaje en el área de chat
            campoTexto.setText(""); // Limpiar campo de texto
        });
    }

    // Metodo para mostrar mensajes recibidos
    public void recibirMensaje(String mensaje) {
        areaMensajes.append(mensaje + "\n");
    }

    // Metodo para actualizar la lista de usuarios conectados
    public void actualizarUsuariosConectados(String[] usuarios) {
        usuariosConectados.removeAllItems(); // Limpiar la lista actual
        for (String usuario : usuarios
        ) {
            usuariosConectados.addItem(usuario);
        }
    }
}
