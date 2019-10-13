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
            ServerSocket sock = new ServerSocket(portNumber);

            int c;
            String responseLine;

            // Run forever, which is common for server style services
            while (true) {
                // Wait until someone connects, thereby requesting a date
                Socket client = sock.accept();
                System.out.println("Someone requested something...");

                // Construct a writer so we can write to the socket, thereby
                // sending something back to the client.
                OutputStream os = client.getOutputStream();
                DataInputStream is = new DataInputStream(client.getInputStream());

                c = is.read();
                os.write(c);
                os.flush();

                // Close the client socket since we're done.
                client.close();
            }

            // *Very* minimal error handling.
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}