// Clase principal que inicia la configuración del cliente
package cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IniciarCliente {
    public static void main(String[] args) {
        // Crear panel para los campos de configuración
        JPanel panelConfiguracion = new JPanel(new GridLayout(3, 2, 10, 10));
        panelConfiguracion.setBorder(new EmptyBorder(15, 15, 15, 15)); // Márgenes
        panelConfiguracion.setBackground(new Color(50, 50, 50)); // Color de fondo

        // Campos de entrada para la configuración del servidor
        JTextField campoIP = new JTextField("192.168."); // Campo para IP del servidor
        JTextField campoUsuario = new JTextField("usuario"); // Campo para nombre de usuario
        JTextField campoPuerto = new JTextField("1234"); // Campo para el puerto

        // Configuración de fuentes para los campos de entrada
        campoIP.setFont(new Font("Verdana", Font.PLAIN, 14));
        campoUsuario.setFont(new Font("Verdana", Font.PLAIN, 14));
        campoPuerto.setFont(new Font("Verdana", Font.PLAIN, 14));

        // Etiquetas para los campos de entrada
        JLabel etiquetaIP = new JLabel("IP del Servidor:");
        JLabel etiquetaUsuario = new JLabel("Nombre de Usuario:");
        JLabel etiquetaPuerto = new JLabel("Puerto:");

        // Configuración de fuentes y colores para las etiquetas
        etiquetaIP.setFont(new Font("Verdana", Font.BOLD, 14));
        etiquetaIP.setForeground(Color.LIGHT_GRAY);
        etiquetaUsuario.setFont(new Font("Verdana", Font.BOLD, 14));
        etiquetaUsuario.setForeground(Color.LIGHT_GRAY);
        etiquetaPuerto.setFont(new Font("Verdana", Font.BOLD, 14));
        etiquetaPuerto.setForeground(Color.LIGHT_GRAY);

        // Añadir componentes al panel
        panelConfiguracion.add(etiquetaIP);
        panelConfiguracion.add(campoIP);
        panelConfiguracion.add(etiquetaUsuario);
        panelConfiguracion.add(campoUsuario);
        panelConfiguracion.add(etiquetaPuerto);
        panelConfiguracion.add(campoPuerto);

        // Mostrar cuadro de diálogo para configurar la conexión
        int resultado = JOptionPane.showConfirmDialog(
                null,
                panelConfiguracion,
                "Conexión al Servidor de Chat",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            // Obtener datos ingresados por el usuario
            String direccionIP = campoIP.getText();
            String textoPuerto = campoPuerto.getText();
            String nombreUsuario = campoUsuario.getText();

            try {
                int puerto = Integer.parseInt(textoPuerto); // Convertir puerto a entero

                // Personalizar apariencia de mensajes emergentes
                UIManager.put("OptionPane.messageFont", new Font("Verdana", Font.PLAIN, 14));
                UIManager.put("OptionPane.background", new Color(50, 50, 50));
                UIManager.put("Panel.background", new Color(50, 50, 50));
                UIManager.put("OptionPane.messageForeground", Color.LIGHT_GRAY);

                // Crear la interfaz del cliente y mostrarla
                new InterfazCliente(direccionIP, puerto, nombreUsuario).setVisible(true);
            } catch (NumberFormatException ex) {
                // Error si el puerto no es un número válido
                JOptionPane.showMessageDialog(null, "El valor del puerto debe ser un número.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Mostrar cualquier otro error al conectar
                JOptionPane.showMessageDialog(null, "Ocurrió un error al conectar: \n" + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.exit(0); // Salir si se cancela la configuración
        }
    }
}
