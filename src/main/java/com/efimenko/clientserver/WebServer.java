package com.efimenko.clientserver;

import java.net.*;
import java.io.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WebServer {
    //static Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String value;
        while (!(value = bufferedReader.readLine()).isEmpty()) {
            System.out.println(value);
        }

        String response = "HTTP/1.1 200 OK\n";
        String content = "\nHello this is my html page";

        response += content;

        OutputStream outputStream = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        writer.write(response);

        writer.close();
    }
}
