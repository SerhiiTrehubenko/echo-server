package com.tsa.echoserver;

import static com.tsa.echoserver.Server.*;
import static com.tsa.echoserver.Client.*;
import static com.tsa.echoserver.ServerRW.*;
import static com.tsa.echoserver.ClientRW.*;
class ServerTest {

    @org.junit.jupiter.api.Test
    void tetStartServer() {
        start(3000);
    }

    @org.junit.jupiter.api.Test
    void tetStartClient() {
        clientGo("localhost",3000);
    }

    @org.junit.jupiter.api.Test
    void tetStartServerRW() {
        startRW(3001);
    }

    @org.junit.jupiter.api.Test
    void tetStartClientRW() {
        clientRWGo("localhost",3001);
    }

}