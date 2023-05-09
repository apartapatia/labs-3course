package l1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    private final int port;
    private DatagramSocket socket;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            socket = new DatagramSocket(port);
            byte[] buffer = new byte[1024];
            System.out.println("Server started on port " + port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());

                // извлекаем никнейм пользователя из пакета
                String nickname = "";
                if (message.contains(":")) {
                    nickname = message.substring(0, message.indexOf(":"));
                }

                if (message.contains(" has joined the chat.")) {
                    // выводим сообщение о подключении пользователя на сервер
                    System.out.println(nickname + " connected.");
                }
                // выводим сообщение в консоль с указанием никнейма пользователя
                System.out.println(nickname + " said: " + message.substring(message.indexOf(":") + 1));

                // добавляем вывод сообщения о подключении пользователя
                if (message.contains(" has joined the chat.")) {
                    System.out.println(nickname + " connected.");
                }

                // добавляем вывод сообщения об отключении пользователя
                if (message.contains(" has left the chat.")) {
                    System.out.println(nickname + " disconnected.");
                }

                buffer = new byte[1024];
            }
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
        Server server = new Server(5000);
        server.start();
    }
}