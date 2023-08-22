package ru.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8085;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String response = in.readLine();
            System.out.println(response);

            while(true){
                Scanner scanner = new Scanner(System.in);
                String name = scanner.nextLine();
                out.println(name);
                response = in.readLine();
                System.out.println(response);
                if(response.contains("Пока"))
                    return;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
