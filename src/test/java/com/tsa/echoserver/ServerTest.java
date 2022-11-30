package com.tsa.echoserver;

import static com.tsa.echoserver.Server.*;
import static com.tsa.echoserver.Client.*;
class ServerTest {

    @org.junit.jupiter.api.Test
    void tetStartServer() {
        start(3000);
    }

    @org.junit.jupiter.api.Test
    void tetStartClient() {
        clientGo("localhost",3000);
    }

}