package com.tsa.echoserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Server {

    static final String STOP = "stop";
    static final String NEXT = "next";
    private static final String ECHO = "echo ";

    public static void start(int port) {

        try (final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4);
                var server = new ServerSocket(port)) {

            while (true) {
                var socket = server.accept();
//                Thread thread = new Thread(() -> answerRequest(socket));
//                thread.start();
                scheduledThreadPoolExecutor.execute(() -> answerRequest(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void answerRequest(Socket socket) {
        String inputMessage = "";
        byte[] buffer = new byte[150];

        try (Socket incomeSocket = socket;
             var input = socket.getInputStream();
             var output = socket.getOutputStream()) {

            while (!Objects.equals(STOP, inputMessage)) {
                int count = 0;
                byte incomeChar = 0;

                while (incomeChar != 13) {
                    incomeChar = (byte) input.read();
                    if (incomeChar != 10 && incomeChar != 13) {
                        buffer[count] = incomeChar;
                        count++;
                    }
                }
                inputMessage = new String(buffer, 0, count);
                String response = ECHO + inputMessage + "\n\r";
                output.write(response.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
