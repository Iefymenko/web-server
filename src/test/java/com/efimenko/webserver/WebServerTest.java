package com.efimenko.webserver;

import org.junit.Test;

import java.io.IOException;

public class WebServerTest {
    @Test
    public void testWebServer() {
        WebServer webServer = new WebServer();
        webServer.setWebAppPath("webapp");
        webServer.setPort(3000);
        try {
            webServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
