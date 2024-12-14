// Clase que maneja la conexión del cliente al servidor
package cliente;

import java.io.*;
import java.net.Socket;

public class ConexionCliente {
    private final Socket clienteSocket; // Socket para la conexión con el servidor
    private final BufferedReader entradaDatos; // Flujo para recibir datos del servidor
    private final PrintWriter salidaDatos; // Flujo para enviar datos al servidor
    private final InterfazCliente interfaz; // Referencia a la interfaz gráfica del cliente

    // Constructor que establece la conexión con el servidor
    public ConexionCliente(String host, int port, String username, InterfazCliente interfaz) throws IOException {
        this.clienteSocket = new Socket(host, port); // Conexión al servidor
        this.salidaDatos = new PrintWriter(clienteSocket.getOutputStream(), true); // Flujo de salida
        this.entradaDatos = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream())); // Flujo de entrada
        this.interfaz = interfaz;

        salidaDatos.println(username); // Enviar el nombre de usuario al servidor

        // Hilo para escuchar y procesar los mensajes entrantes
        new Thread(() -> {
            try {
                String mensaje;
                while ((mensaje = entradaDatos.readLine()) != null) {
                    // Procesar mensajes según su prefijo
                    if (mensaje.startsWith("MENSAJE:")) {
                        interfaz.recibirMensaje(mensaje.substring(8)); // Mostrar mensaje recibido
                    } else if (mensaje.startsWith("USUARIOS:")) {
                        // Actualizar lista de usuarios conectados
                        interfaz.actualizarUsuariosConectados(mensaje.substring(9).split(","));
                    }
                }
            } catch (IOException e) {
                // Mostrar mensaje de error si se pierde la conexión
                interfaz.recibirMensaje("Conexión perdida con el servidor.");
            }
        }).start(); // Inicia el hilo
    }

    // Método para enviar un mensaje al servidor
    public void enviarMensaje(String destino, String contenido) {
        salidaDatos.println(destino + ":" + contenido); // Formato: destino:mensaje
    }
}
