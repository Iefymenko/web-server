package com.efimenko.webserver;

import java.net.*;
import java.io.*;

public class WebServer {
    //static Logger logger = Logger.getLogger(Server.class.getName());

    private ServerSocket serverSocket;
    private int port;
    private String webAppPath;

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

        String response = "HTTP/1.1 200 OK\n\n";
        //String content = "Hello this is my html page";

        //cd ..\..\..\..\webapp\index.html

        //response += content;

        File fileFrom = new File("C:\\Work\\web-server\\src\\main\\webapp\\index.html");

        OutputStream outputStream = socket.getOutputStream();
        //Writer writer = new OutputStreamWriter(outputStream);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        //writer.write(response);

        bos.write(response.getBytes(), 0, response.length());

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileFrom));

            byte[] buffer = new byte[1000];
            int numBytes;
            while ((numBytes = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, numBytes);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        bos.close();
    }

}
