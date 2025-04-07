package Tutorial3.Extended;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleChatServer {
    private static final Logger logger=Logger.getLogger(SimpleChatServer.class.getName());

    static {
        try {
            // Set up FileHandler to save logs to "server_logs.txt" with append mode (true)
            FileHandler fileHandler = new FileHandler("server_logs.txt", true);

            // Use SimpleFormatter for human-readable logs
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Add the FileHandler to our Logger instance
            logger.addHandler(fileHandler);

            // Set the logging level
            logger.setLevel(Level.ALL);

            logger.info("Logger initialized for SimpleChatServer");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to set up file handler for logger", e);
        }
    }
    public static void main(String[] args) {
        logger.info("Server application is started");
        try {
            //Create a new instance of the Server Socket and pass port number
            ServerSocket serverSocket = new ServerSocket(12345);

            //print out a message to say Server is running
            logger.info("Server is running on the port of 12345 and waiting for the client");
            System.out.println("Server is running and waiting for client connection...");

            // Wait for a client to connect and accept the client request
            Socket clientSocket = serverSocket.accept();

            //print out a message to say client connected and get the IP address
            String clientAddress=clientSocket.getInetAddress().getHostAddress();
            System.out.println("Client connected: " + clientAddress);
            logger.info("Client connected from IP address: " + clientAddress);

            // Input stream to receive messages from the client
            InputStream inputStream = clientSocket.getInputStream();

            // Output stream to send messages to the client
            OutputStream outputStream = clientSocket.getOutputStream();

            logger.info("Input and output streams are established");

            //Create a buffer array with type byte, the size must be 1024
            byte[] buffer = new byte[1024];

            // Read messages from the client and print them
            int bytesRead;
            logger.info("Starting message exchange loop");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                System.out.println("Client says: " + message);
                logger.info("Received message from client: " + message);

                // Echo the message back to the client
                String response = "Server received: " + message;
                outputStream.write(response.getBytes());
                logger.info("Sent response to client: " + response);

            }

            // Close the sockets
            logger.info("Client disconnected, closing sockets");
            clientSocket.close();
            serverSocket.close();
            logger.info("Server sockets closed successfully");


        } catch (IOException e) {
            //catch IO exception
            logger.log(Level.SEVERE, "IO Exception occurred", e);
            e.printStackTrace();
        }
        logger.info("Server application terminated");
    }
}