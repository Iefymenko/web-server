package com.efimenko.webserver;

import java.net.*;
import java.io.*;

public class WebServer {

    private String webAppPathRelative;
    private WebServerFile webServerFile;
    private ServerSocket serverSocket;
    private int port;
    private static final String RESPONSE_200 = "HTTP/1.1 200 OK\n\n";
    private static final String RESPONSE_404 = "HTTP/1.1 404 Not Found\n\n";

    private int DefaultPort(){
        return 3000;
    }

    public void setPort(int newPort) {
        port = newPort;
    }

    public void setWebAppPath(String newWebAppPath){
        webAppPathRelative = newWebAppPath;
    }


    WebServer(){
        serverSocket = null;
        port = DefaultPort();
    }

    public void start() throws IOException{
        webServerFile = new WebServerFile(webAppPathRelative);
        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String value;
            value = bufferedReader.readLine();
            String arr[] = value.split(" ", 3);

            if ("GET".equals(arr[0])) {

                File fileFrom = webServerFile.getFile(arr[1]);

                OutputStream outputStream = socket.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(outputStream);

                try {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileFrom));

                    bos.write(RESPONSE_200.getBytes(), 0, RESPONSE_200.length());

                    byte[] buffer = new byte[1000];
                    int numBytes;
                    while ((numBytes = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, numBytes);
                    }
                }
                catch (FileNotFoundException e) {
                    String response = RESPONSE_404;
                    response += "<html><body><h1>404 Page not found!!</h1></body></html>";
                    bos.write(response.getBytes(), 0, response.length());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                bos.close();

            }

        }


    }

}
