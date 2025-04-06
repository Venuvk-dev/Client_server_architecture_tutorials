package Tutorial3.Extended;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleChatServer {
    public static void main(String[] args) {
        try {
            //Create a new instance of the Server Socket and pass port number
            ServerSocket serverSocket = new ServerSocket(12345);

            //print out a message to say Server is running
            System.out.println("Server is running and waiting for client connection...");

            // Wait for a client to connect and accept the client request
            Socket clientSocket = serverSocket.accept();

            //print out a message to say client connected and get the IP address
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // Input stream to receive messages from the client
            InputStream inputStream = clientSocket.getInputStream();

            // Output stream to send messages to the client
            OutputStream outputStream = clientSocket.getOutputStream();

            //Create a buffer array with type byte, the size must be 1024
            byte[] buffer = new byte[1024];

            // Read messages from the client and print them
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                System.out.println("Client says: " + message);

                // Echo the message back to the client
                String response = "Server received: " + message;
                outputStream.write(response.getBytes());
            }

            // Close the sockets
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            //catch IO exception
            e.printStackTrace();
        }
    }
}