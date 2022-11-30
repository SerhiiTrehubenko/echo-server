package com.tsa.echoserver;

import java.net.ServerSocket;
import java.util.Objects;

public class Server {

    static final String STOP = "stop";

    static final String NEXT = "next";

    private static final String ECHO = "echo ";

    public static void start(int port) {
        try (var server = new ServerSocket(port)) {
            String inputMessage = "";
            byte[] buffer = new byte[1024];

            while (!Objects.equals(STOP, inputMessage)) {
                inputMessage = "";
                try (var socket = server.accept();
                     var input = socket.getInputStream();
                     var output = socket.getOutputStream()) {

                    while (!Objects.equals(NEXT, inputMessage) & !Objects.equals(STOP, inputMessage)) {
                        int count = input.read(buffer);
                        inputMessage = new String(buffer, 0, count);
                        String response = ECHO + inputMessage;
                        output.write(response.getBytes());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
