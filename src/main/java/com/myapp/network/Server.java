package com.myapp.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            // Krok 1: Serwer wysyła do klienta wiadomość "ready"
            outputStream.writeUTF("ready");
            outputStream.flush();

            // Krok 2: Klient wysyła dla serwera Int n
            int n = inputStream.readInt();
            System.out.println("Received n from client: " + n);

            // Krok 3: Serwer wysyła dla klienta "ready for n"
            outputStream.writeUTF("ready for " + n);
            outputStream.flush();

            // Krok 4: Serwer odbiera n obiektów klasy Message od klienta
            for (int i = 0; i < n; i++) {
                // Odbieranie obiektu message od klienta
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Message message = (Message) objectInputStream.readObject();

                System.out.println("Received message " + message.getNumber() + " from client");
                System.out.println("Message content: " + message.getContent());
            }


            // Krok 5: Serwer wysyła dla klienta "finished"
            outputStream.writeUTF("finished");
            outputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


