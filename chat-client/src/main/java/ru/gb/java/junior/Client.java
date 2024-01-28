package ru.gb.java.junior;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private String name;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public Client(Socket socket, String name) {
        try {
            this.socket = socket;
            this.name = name;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            allClose(socket, bufferedReader, bufferedWriter);
        }
    }

    public void readMessageFromServer() {

        new Thread(() -> {
            String message;
            while (socket.isConnected()) {
                try {
                    message = bufferedReader.readLine();
                    System.out.println(message);
                } catch (IOException e) {
                    allClose(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }

    public void sendMessage() {
        String message;
        Scanner scanner = new Scanner(System.in);
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            while (socket.isConnected()) {
                message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            allClose(socket, bufferedReader, bufferedWriter);
            scanner.close();

        }

    }

    private void allClose(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            new RuntimeException("В работе клиента " + name + " что то пошло не так");
        }
    }
}
