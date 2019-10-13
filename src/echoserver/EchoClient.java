package echoserver;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {

    public static final int portNumber = 6013;

    public static void main(String[] args) {

        String server;

        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            Socket echoSocket = new Socket(server, portNumber);
            OutputStream os = echoSocket.getOutputStream();
            DataInputStream is = new DataInputStream(echoSocket.getInputStream());

            int c;
            int responseByte;

            while ( (c = System.in.read()) != -1) {
                os.write((byte)c);
                if (c == '\n') {
                    os.flush();
                    responseByte = is.read();
                    System.out.println("echo: " + responseByte);
                }
            }

            System.out.println("Time to close.");

            os.close();
            is.close();
            echoSocket.close();
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
    }


}