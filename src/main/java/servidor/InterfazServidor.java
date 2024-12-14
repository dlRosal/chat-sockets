// Clase que representa la interfaz gráfica del panel de control del servidor
package servidor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InterfazServidor extends JFrame {
    private final JTextArea areaRegistro; // Área de texto donde se mostrarán los logs

    // Constructor de la clase
    public InterfazServidor() {
        // Configuración de la ventana principal
        setTitle("Panel de Control del Servidor"); // Título de la ventana
        setSize(700, 500); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana

        // Crear un panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado interno del panel
        panelPrincipal.setBackground(new Color(30, 30, 30)); // Color de fondo del panel

        // Crear el área de texto para mostrar los logs del servidor
        areaRegistro = new JTextArea();
        areaRegistro.setEditable(false); // Hacer que el área de texto no sea editable
        areaRegistro.setFont(new Font("Consolas", Font.PLAIN, 14)); // Fuente monoespaciada para los logs
        areaRegistro.setForeground(Color.WHITE); // Color de texto blanco
        areaRegistro.setBackground(new Color(50, 50, 50)); // Color de fondo del área de texto

        // Crear un JScrollPane para el área de texto (permitir desplazamiento si el log es largo)
        JScrollPane scrollPane = new JScrollPane(areaRegistro);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Bordear el JScrollPane con un color gris

        // Añadir el JScrollPane al panel principal
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Añadir el panel principal a la ventana
        add(panelPrincipal);
    }

    // Metodo para agregar mensajes al área de texto (registro de logs)
    public void registrarLog(String mensaje) {
        areaRegistro.append(mensaje + "\n"); // Agregar el mensaje al final del área de texto
    }
}
