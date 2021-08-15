package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OthelloServer {
    public static void main(String[] args) {
        int port = 61050;

        try {
            if (args.length != 0) {
                port = Integer.parseInt(args[0]);
            } else {
                System.out.println("Using default port: 61050");
            }
            int i = 1;
            ServerSocket s = new ServerSocket(port);
            System.out.println("Now listening to port: " + port);

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Inbound connection #" + i);
                Runnable r = new OthelloServerThread(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
