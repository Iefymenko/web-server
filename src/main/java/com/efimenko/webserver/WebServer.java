package com.efimenko.webserver;

import java.net.*;
import java.io.*;

public class WebServer {
    //static Logger logger = Logger.getLogger(Server.class.getName());

    private ServerSocket serverSocket;
    int port;
    String webAppPath;

    private int DefaultPort(){
        return 3000;
    }

    private String DefaultWebbAppPath(){
        return ".";
    }

    public void setPort(int newPort) {
        port = newPort;
    }

    public void setWebAppPath(String newWebAppPath){
        webAppPath = newWebAppPath;
    }


    public WebServer(){
        serverSocket = null;
        port = DefaultPort();
        webAppPath = DefaultWebbAppPath();
    }

    public void start() throws IOException{
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

    /*public static void main(String[] args) throws IOException{
        //ServerSocket serverSocket = null;
    }*/
}
