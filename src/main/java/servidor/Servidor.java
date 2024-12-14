// Clase ServidorServidor
package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Servidor extends Thread {
    private final int puertoServidor; // Puerto en el que escucha el servidor
    private final InterfazServidor interfazServidor; // Interfaz gráfica para mostrar el registro
    private final List<ManejadorCliente> clientesConectados; // Lista de clientes conectados

    // Constructor para inicializar el puerto del servidor y la interfaz gráfica
    public Servidor(int puertoServidor, InterfazServidor interfazServidor) {
        this.puertoServidor = puertoServidor;
        this.interfazServidor = interfazServidor;
        this.clientesConectados = new CopyOnWriteArrayList<>(); // Usamos CopyOnWriteArrayList para garantizar la seguridad de los hilos
    }

    // Método run() que se ejecuta cuando se inicia el hilo del servidor
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(puertoServidor)) {
            interfazServidor.registrarLog("Servidor iniciado en el puerto " + puertoServidor); // Mensaje de inicio en la interfaz

            while (true) {
                // Acepta las conexiones entrantes de los clientes
                Socket clienteSocket = serverSocket.accept();
                interfazServidor.registrarLog("Nueva conexión desde: " + clienteSocket.getInetAddress());

                // Crea un manejador para cada cliente
                ManejadorCliente cliente = new ManejadorCliente(clienteSocket, this);
                clientesConectados.add(cliente); // Añadir el cliente a la lista de clientes conectados
                cliente.start(); // Inicia un hilo para manejar la comunicación con el cliente
            }
        } catch (Exception ex) {
            // Si ocurre un error, se muestra en la interfaz
            interfazServidor.registrarLog("Error al iniciar el servidor: " + ex.getMessage());
        }
    }

    // Método sincronizado para enviar un mensaje a un cliente específico
    public synchronized void enviarMensaje(String remitente, String destinatario, String mensaje) {
        for (ManejadorCliente cliente : clientesConectados) {
            // Si el nombre de usuario del cliente coincide con el destinatario
            if (cliente.obtenerNombreUsuario().equals(destinatario)) {
                cliente.enviarMensaje("De " + remitente + ": " + mensaje); // Envia el mensaje al cliente
                break; // Solo se envía al primer cliente que coincida
            }
        }
    }

    // Método sincronizado para actualizar la lista de usuarios activos
    public synchronized void actualizarUsuariosActivos() {
        List<String> usuarios = new CopyOnWriteArrayList<>();
        // Añade todos los usuarios a la lista
        for (ManejadorCliente cliente : clientesConectados) {
            usuarios.add(cliente.obtenerNombreUsuario());
        }
        // Envía la lista de usuarios activos a todos los clientes
        for (ManejadorCliente cliente : clientesConectados) {
            cliente.enviarListaUsuarios(usuarios);
        }
    }

    // Método sincronizado para desconectar un cliente
    public synchronized void desconectarCliente(ManejadorCliente cliente) {
        clientesConectados.remove(cliente); // Elimina al cliente de la lista
        interfazServidor.registrarLog("Cliente desconectado: " + cliente.obtenerNombreUsuario()); // Muestra en la interfaz que el cliente se desconectó
        actualizarUsuariosActivos(); // Actualiza la lista de usuarios activos
    }
}
