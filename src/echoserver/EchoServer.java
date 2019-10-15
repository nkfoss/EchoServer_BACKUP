package echoserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
            // Start listening on the specified port
            ServerSocket serverSocket = new ServerSocket(portNumber);

            int c;
            String responseLine;

            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects, thereby requesting a date
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from client.");

                // Construct a writer so we can write to the socket, thereby
                // sending something back to the client.
                OutputStream os = clientSocket.getOutputStream();
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());

                // c = is.read();
                while ( (c = is.read()) != 0) {
                    os.write(c);
                    os.flush();
                }

                // Close the client socket since we're done.
                System.out.println("Closing client connection");
                os.close();
                is.close();
                clientSocket.close();

            }

            // *Very* minimal error handling.
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}