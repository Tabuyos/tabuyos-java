package com.tabuyos.java.learn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author Tabuyos
 * @Time 2/26/20 2:25 PM
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class QQClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        System.out.println("Input: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        socket.getOutputStream().write(text.getBytes());
    }
}
