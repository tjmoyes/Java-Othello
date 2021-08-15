package othello;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class OthelloNetworkController extends Thread {
    Socket s;
    int port;
    String host;

    OthelloNetworkController(String host, int port) {
        System.out.println("In ONC constructor");
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(InetAddress.getByName(host), port), 10000);
            s.setSoTimeout(10000);
            try {
                InputStream inStream = s.getInputStream();
                Scanner in = new Scanner(inStream);

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println(line);
                }

                in.close();
            } finally {
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}