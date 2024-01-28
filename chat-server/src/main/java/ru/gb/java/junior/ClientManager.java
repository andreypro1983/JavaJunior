package ru.gb.java.junior;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;


public class ClientManager implements Runnable {
    private Socket socket;
    private String name;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.name = bufferedReader.readLine();
            clients.add(this);
            System.out.println("Клиент " + name + " подключился к чату");
            broadcastSend("Server: клиент " + name + " подключился к чату");
        } catch (IOException e) {
            allClose(socket, bufferedReader, bufferedWriter);
        }
    }


    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) {
                    allClose(socket, bufferedReader, bufferedWriter);
                    break;
                }
                sendMessage(messageFromClient);
            } catch (IOException e) {
                allClose(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }


    private boolean checkPrivateMessage(String message) {
        return message.startsWith("@");
    }

    private boolean checkLengthMessage(String message) {
        try {
            return message.trim().indexOf(" ") > 1;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private void sendMessage(String message) {
        try {
            if (checkPrivateMessage(message) && checkLengthMessage(message)) {
                Optional<ClientManager> optionalClient = clients.stream()
                        .filter(x -> x.name.equals(message.substring(1, message.indexOf(" "))))
                        .findFirst();
                if (optionalClient.isPresent()) {
                    ClientManager client = optionalClient.get();
                    if (!client.name.equals(name)) {
                        client.bufferedWriter.write("@" + name + ": " + message.substring(message.trim().indexOf(" ") + 1));
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                } else {
                    bufferedWriter.write("Отправка приватного сообщения не удалась. Пользователь "
                            + message.substring(1, message.indexOf(" ")) + " не найден");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }

            } else if (checkPrivateMessage(message)) {
                bufferedWriter.write("Отправка приватного сообщения не удалась." +
                        " Отсутствует текст для отправки пользователю");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else {
                broadcastSend(name + ": " + message);
            }
        } catch (IOException e) {
            allClose(socket, bufferedReader, bufferedWriter);
        }
    }

    private void broadcastSend(String message) {

        for (ClientManager client : clients) {
            try {
                if (!(client.name.equals(name)) && message != null) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException e) {
                allClose(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println("Клиент " + name + " покинул чат");
        broadcastSend("Server: клиент " + name + " покинул чат");
    }

    private void allClose(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
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
