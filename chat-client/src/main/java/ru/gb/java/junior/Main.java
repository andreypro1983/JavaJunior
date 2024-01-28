package ru.gb.java.junior;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Введите ваше имя:");
            String name = sc.nextLine();
            InetAddress address = InetAddress.getLocalHost();
            Socket socket = new Socket(address, 4500);
            Client client = new Client(socket, name);
            client.readMessageFromServer();
            client.sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
            sc.close();
        }
    }
}