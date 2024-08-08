import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class Client {
    static String UserName;
    static JTextArea incoming;
    static JTextField outgoing;
    static BufferedReader reader;
    static PrintWriter writer;

    static Socket socket;

    public static void main(String[] args) {
        while (UserName == null) {

            System.out.println("Enter Username");
            Scanner scan = new Scanner(System.in);
            UserName = scan.nextLine();
        }
        setupGUI();

        ClientGo();

        Thread thread = new Thread(new IncomingMessage());

        thread.start();

    }

    public static void setupGUI() {
        JFrame frame = new JFrame("Chatty Chat server" + "[" + UserName + "]");
        JPanel panel = new JPanel();
        incoming = new JTextArea(15, 50);
        Font font = new Font("ARIAL", Font.PLAIN, 15);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane scrollpane = new JScrollPane(incoming);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton send = new JButton("Send");
        
        incoming.setFont(font);
        outgoing.setFont(font);
        panel.add(scrollpane);
        panel.add(send);
        panel.add(outgoing);
        send.addActionListener(new Outgoingmessage());
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
        
    }

    public static void ClientGo() {
        try {
            socket = new Socket("172.16.172.176", 4545);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Client Connnected");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static class IncomingMessage implements Runnable {
        public void run() {
            String message;

            try {
                while ((message = reader.readLine()) != null) {

                    incoming.append(message + "\n");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static class Outgoingmessage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                System.out.println(outgoing.getText());

                writer.println(UserName + "=> " + outgoing.getText());
                writer.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
