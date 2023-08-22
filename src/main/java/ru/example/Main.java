package ru.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server started");

        try(ServerSocket serverSocket = new ServerSocket(8085)){
            while(true){
                try(Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted");

                    out.println("Введите ваше имя:");
                    final String name = in.readLine();
                    out.println(String.format("%s, тебе есть 18 лет?", name));
                    while(!clientSocket.isClosed()) {
                        final String text = in.readLine();
                        if(text.equals("да")){
                            out.println(String.format("%s, можешь пить пиво! Пока!", name));
                            clientSocket.close();
                        }else if(text.equals("нет")){
                            out.println(String.format("%s, можешь пить сок! Пока!", name));
                            clientSocket.close();
                        }else{
                            out.println("Не знаю такой команды, попробуй еще раз!");
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}