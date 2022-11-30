package com.tsa.echoserver;

import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

import static com.tsa.echoserver.Server.NEXT;
import static com.tsa.echoserver.Server.STOP;


public class Client {

    public static void clientGo(String ip, int port) {

        try (var socket = new Socket(ip, port);
             var input = socket.getInputStream();
             var output = socket.getOutputStream();
             var scanner = new Scanner(System.in)) {

            String outputWord = "";
            byte[] incomeBytes = new byte[1024];

            while (!Objects.equals(NEXT, outputWord) & !Objects.equals(STOP, outputWord)) {
                System.out.print("\nInput a message: ");
                outputWord = scanner.nextLine();
                output.write(outputWord.getBytes());
                int count = input.read(incomeBytes);
                System.out.println("Server response: " + new String(incomeBytes, 0, count));

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
