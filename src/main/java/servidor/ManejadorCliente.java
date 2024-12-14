// Clase que maneja la conexión y la comunicación con un cliente en el servidor
package servidor;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ManejadorCliente extends Thread {
    private final Socket socketConexion; // Socket para la conexión con el cliente
    private final Servidor servidorGestor; // Referencia al servidor para gestionar la conexión
    private BufferedReader lectorSocket; // Para leer datos enviados por el cliente
    private PrintWriter escritorSocket; // Para enviar datos al cliente
    private String usuarioConectado; // Nombre de usuario del cliente

    // Constructor para inicializar el manejador de cliente con su socket y el servidor
    public ManejadorCliente(Socket socketConexion, Servidor servidorGestor) {
        this.socketConexion = socketConexion;
        this.servidorGestor = servidorGestor;
    }

    // Método run() que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        try {
            // Establecer los flujos de entrada y salida
            lectorSocket = new BufferedReader(new InputStreamReader(socketConexion.getInputStream()));
            escritorSocket = new PrintWriter(socketConexion.getOutputStream(), true);

            // Recibe el nombre del cliente al inicio
            usuarioConectado = lectorSocket.readLine();
            // Actualiza la lista de usuarios activos en el servidor
            servidorGestor.actualizarUsuariosActivos();

            String mensajeEntrada;
            // Leer mensajes del cliente hasta que cierre la conexión
            while ((mensajeEntrada = lectorSocket.readLine()) != null) {
                String[] partesMensaje = mensajeEntrada.split(":", 2);
                // Si el mensaje está bien formado (nombre:mensaje), enviarlo al servidor
                if (partesMensaje.length == 2) {
                    servidorGestor.enviarMensaje(usuarioConectado, partesMensaje[0], partesMensaje[1]);
                }
            }
        } catch (IOException e) {
            // Si hay un error de IO, desconectar al cliente
            servidorGestor.desconectarCliente(this);
        }
    }

    // Método para obtener el nombre de usuario del cliente
    public String obtenerNombreUsuario() {
        return usuarioConectado;
    }

    // Método para enviar un mensaje al cliente
    public void enviarMensaje(String mensaje) {
        escritorSocket.println("MENSAJE:" + mensaje); // Precede el mensaje con "MENSAJE:"
    }

    // Método para enviar la lista de usuarios conectados al cliente
    public void enviarListaUsuarios(List<String> listaUsuarios) {
        escritorSocket.println("USUARIOS:" + String.join(",", listaUsuarios)); // Lista separada por comas
    }
}
