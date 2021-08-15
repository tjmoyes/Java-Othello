package server;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.PrintWriter;

public class OthelloServerThread implements Runnable {
    private Socket incoming;

    public OthelloServerThread(Socket i) {
        incoming = i;
    }

    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true);

                out.println("Hello! Enter BYE to exit. ");

                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    System.out.println("Client: " + line);
                    if (line.trim().equals("/bye")) {
                        done = true;
                    }
                }
                in.close();
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
