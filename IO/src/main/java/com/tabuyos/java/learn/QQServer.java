package com.tabuyos.java.learn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Tabuyos
 * @Time 2/26/20 2:17 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class QQServer {

    static byte[] bytes = new byte[1024];

    public static void main(String[] args) throws IOException {
        // listen
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        while (true) {
            System.out.println("Wait connect.");
            // block
            Socket socket = serverSocket.accept();
            System.out.println("Connect successful.");
            System.out.println("Wait data.");
            // block
            int read = socket.getInputStream().read(bytes);
            System.out.println("Data successful.");
            String content = new String(bytes);
            System.out.println(content);
        }

    }
}
