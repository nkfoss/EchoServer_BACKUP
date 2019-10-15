package echoserver;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {

    public static final int portNumber = 6013;

    public static void main(String[] args) {

        String server = "127.0.0.1";

        // Use "127.0.0.1", i.e., localhost, if no server is specified.
//        if (args.length == 0) {
//            server = "127.0.0.1";
//        } else {
//            server = args[0];
//        }

        try {
            Socket echoSocket = new Socket(server, portNumber);
            OutputStream os = echoSocket.getOutputStream();
            DataInputStream is = new DataInputStream(echoSocket.getInputStream());

            int c;
            int responseByte;

            while ( (c = System.in.read()) != -1) {
                os.write(c);
                os.flush();
                responseByte = is.read();
                System.out.write(responseByte);
            }

//            System.out.println("Time to close.");

            os.close();
            is.close();
            echoSocket.close();
        }

        catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
    }


}


// model: https://introcs.cs.princeton.edu/java/84network/EchoClient.java.html