package ru.gb.java.junior;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4500);
            Server server = new Server(serverSocket);
            server.serverStart();
        } catch (IOException e) {
            System.out.println("Не удалось запустить сервер");
        }
    }
}