// Server for Chat Room

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

public class Server {

    static Socket socket;

    static ArrayList<PrintWriter> ClientOutputStream = new ArrayList<PrintWriter>();

    public static void main(String[] args) {

        ServerGo();
    }

    public static void ServerGo() {
        ConnectClient();

    }

    public static void ConnectClient() {
        try {

            ServerSocket serversocket = new ServerSocket(4545);

            while (true) {
                Socket socket = serversocket.accept();

                System.out.println("Client Connected ");

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
                ClientOutputStream.add(writer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ClientHandler implements Runnable {
        BufferedReader reader;
        String UserName;

        public ClientHandler(Socket socket) {
            try {
                InputStreamReader ReadStream = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(ReadStream);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void run() {
            String message;

            try {

                while ((message = reader.readLine()) != null) {

                    broadcastMessage(message);

                }
            } catch (IOException e) {
                e.printStackTrace();

            }

        }

        public static void broadcastMessage(String message) {

            for (int i = 0; i < ClientOutputStream.size(); i++) {
                PrintWriter writer = ClientOutputStream.get(i);

                writer.println(message);
                writer.flush();

            }

        }

    }
}