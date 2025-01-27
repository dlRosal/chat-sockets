# Proyecto ChatSockets

¡Bienvenido a ChatSockets! Este proyecto es una aplicación de chat basada en sockets desarrollada en **Java**. Permite la comunicación en tiempo real entre clientes mediante la implementación de un servidor centralizado y múltiples clientes conectados.

## 🚀 Características principales

- **Servidor centralizado**:
  - Gestiona múltiples conexiones simultáneas.
  - Enruta los mensajes entre los clientes conectados.
- **Clientes interactivos**:
  - Cada cliente puede enviar y recibir mensajes en tiempo real.
  - Interfaz de usuario en consola para mayor simplicidad.
- **Arquitectura basada en sockets**:
  - Comunicación mediante sockets TCP/IP.
  - Protocolos personalizados para el manejo de mensajes.

## 📂 Estructura del proyecto

### Código principal
- **Server.java**: Implementación del servidor que gestiona las conexiones de los clientes.
- **Client.java**: Implementación de un cliente que se conecta al servidor y envía/recibe mensajes.
- **MessageHandler.java**: Gestión de los mensajes enviados y recibidos.

### Configuración
- **pom.xml**: Archivo de configuración de Maven para gestionar las dependencias del proyecto.
- **.gitignore**: Exclusión de archivos innecesarios del repositorio.

### Recursos adicionales
- **documentación/**: Archivos explicativos para entender el flujo de la aplicación.

## 💻 Requisitos

- **Java 8+**: Asegúrate de tener instalado el JDK.
- **Maven**: Para la gestión de dependencias y compilación del proyecto.
- **Red local o acceso a Internet**: Necesario para conectar los clientes con el servidor.

## ⚙️ Instrucciones de uso

1. **Clonar el repositorio**:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```

2. **Abrir el proyecto**:
   - Importa el proyecto en tu IDE favorito.

3. **Configurar dependencias**:
   - Ejecuta el siguiente comando para instalar las dependencias:
     ```bash
     mvn install
     ```

4. **Ejecutar el servidor**:
   - Compila y ejecuta `Server.java` desde tu IDE o mediante Maven:
     ```bash
     mvn exec:java -Dexec.mainClass="Server"
     ```

5. **Ejecutar los clientes**:
   - Compila y ejecuta `Client.java` en diferentes terminales o máquinas:
     ```bash
     mvn exec:java -Dexec.mainClass="Client"
     ```

6. **Iniciar el chat**:
   - Conecta los clientes al servidor proporcionando la dirección IP y el puerto configurado.
   - Intercambia mensajes en tiempo real.

## 📈 Futuras mejoras

- Implementar una interfaz gráfica de usuario (GUI) para los clientes.
- Añadir cifrado de mensajes para mayor seguridad.
- Integrar soporte para salas de chat múltiples.

---

¡Explora y mejora el proyecto ChatSockets! Si tienes ideas o deseas contribuir, no dudes en participar.

## 📝 Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
