package com.tsa.echoserver;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

import static com.tsa.echoserver.Server.NEXT;
import static com.tsa.echoserver.Server.STOP;


public class ClientRW {

    public static void clientRWGo(String ip, int port) {

        try (var socket = new Socket(ip, port);
             var input = new InputStreamReader(socket.getInputStream());
             var output = new OutputStreamWriter(socket.getOutputStream());
             var scanner = new Scanner(System.in)) {

            String outputWord = "";
            char[] incomeBytes = new char[1024];

            while (!Objects.equals(NEXT, outputWord) & !Objects.equals(STOP, outputWord)) {
                System.out.print("\nInput a message: ");
                outputWord = scanner.nextLine();
                output.write(outputWord);
                output.flush();

                int count = input.read(incomeBytes);
                System.out.println("Server response: " + new String(incomeBytes, 0, count));

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
