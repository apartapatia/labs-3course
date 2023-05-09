package l1;

import java.io.IOException;
import java.net.*;

public class Client {
    private final String hostname;
    private final int port;
    private DatagramSocket socket;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try {
            socket = new DatagramSocket();
            String input = "";
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println("Client started");

            // добавляем возможность ввода ника
            System.out.print("Enter your nickname:");
            String nickname = System.console().readLine();


            String message = nickname + " has joined the chat.";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            while (!input.equalsIgnoreCase("/exit")) {
                input = System.console().readLine();
                message = "[" + nickname + "]: " + input;
                buffer = message.getBytes();
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);
            }

            // добавляем отправку сообщения об отключении
            message = "[" + nickname + "] has left the chat.";
            buffer = message.getBytes();
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            socket.close();
            System.exit(0);
        } catch (SocketException e) {
            System.out.println("Socket error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 5000);
        client.start();
    }
}