package com.tsa.echoserver;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.util.Objects;

public class ServerRW {

    static final String STOP = "stop";

    static final String NEXT = "next";

    private static final String ECHO = "echo ";

    public static void startRW(int port) {
        try (var server = new ServerSocket(port)) {
            String inputMessage = "";
            char[] buffer = new char[1024];

            while (!Objects.equals(STOP, inputMessage)) {
                inputMessage = "";
                try (var socket = server.accept();
                     var input = new InputStreamReader(socket.getInputStream());
                     var output = new OutputStreamWriter(socket.getOutputStream())) {

                    while (!Objects.equals(NEXT, inputMessage) & !Objects.equals(STOP, inputMessage)) {
                        int count = input.read(buffer);

                        inputMessage = new String(buffer, 0, count);
                        String response = ECHO + inputMessage;

                        output.write(response);
                        output.flush();
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
