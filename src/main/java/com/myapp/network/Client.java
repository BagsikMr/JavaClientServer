package com.myapp.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to server: " + clientSocket);

            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Krok 1: Klient odbiera wiadomość "ready" od serwera
            String readyMessage = inputStream.readUTF();
            System.out.println("Received message from server: " + readyMessage);

            // Krok 2: Klient wysyła Int n do serwera
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the value of n: ");
            int n = scanner.nextInt();
            outputStream.writeInt(n);
            outputStream.flush();

            // Krok 3: Klient odbiera wiadomość "ready for n" od serwera
            String readyForNMessage = inputStream.readUTF();
            System.out.println("Received message from server: " + readyForNMessage);

            // Krok 4: Klient wysyła n obiektów klasy Message jako kolejnych obiektów
            for (int i = 0; i < n; i++) {
                Message message = new Message();
                // Ustawienie odpowiednich wartości dla pola number i content
                message.setNumber(i + 1);
                message.setContent("Message " + (i + 1));

                // Wysyłanie obiektu message do serwera
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();

                System.out.println("Sent message " + (i + 1) + " to server");
            }


            // Krok 5: Klient odbiera wiadomość "finished" od serwera
            String finishedMessage = inputStream.readUTF();
            System.out.println("Received message from server: " + finishedMessage);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



