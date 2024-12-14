// Clase principal que configura e inicia el servidor
package servidor;

import javax.swing.*;
import java.awt.*;

public class IniciarServidor {
    public static void main(String[] args) {
        // Configuración de colores personalizados para los cuadros de mensaje de JOptionPane
        UIManager.put("OptionPane.background", Color.BLACK); // Fondo negro
        UIManager.put("Panel.background", Color.BLACK); // Fondo del panel negro
        UIManager.put("OptionPane.messageForeground", Color.WHITE); // Mensajes en color blanco
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14)); // Fuente Arial de tamaño 14
        UIManager.put("OptionPane.foreground", Color.WHITE); // Color del texto de los mensajes
        UIManager.put("Button.background", Color.DARK_GRAY); // Botones con fondo gris oscuro
        UIManager.put("Button.foreground", Color.WHITE); // Texto de los botones en blanco

        // Crear un JOptionPane para pedir al usuario el puerto para el servidor
        JOptionPane pane = new JOptionPane();
        pane.setBackground(Color.BLACK); // Fondo negro para el panel emergente

        // Mostrar un cuadro de entrada para que el usuario ingrese el puerto
        String puertoTexto = JOptionPane.showInputDialog(null,
                "Especifique el puerto para el servidor:", // Mensaje que se muestra
                "Input", // Título de la ventana emergente
                JOptionPane.PLAIN_MESSAGE); // Tipo de mensaje

        // Crear la interfaz gráfica para el servidor
        InterfazServidor interfazAdmin = new InterfazServidor();
        interfazAdmin.setVisible(true); // Hacer visible la interfaz del servidor

        try {
            // Intentar convertir el texto ingresado en un número entero (puerto)
            int puerto = Integer.parseInt(puertoTexto);
            // Iniciar el servidor en un hilo separado, pasando el puerto y la interfaz gráfica
            new Servidor(puerto, interfazAdmin).start();
        } catch (NumberFormatException e) {
            // Si el valor ingresado no es un número válido, registrar el error en la interfaz
            interfazAdmin.registrarLog("Error: Debe ingresar un número válido para el puerto.");
        }
    }
}
