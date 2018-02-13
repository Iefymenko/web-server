package com.efimenko.webserver;

import java.io.*;
import java.util.HashMap;

/**
 * Class to store all File objects
 */

public class WebServerFile {

    private String webAppPathAbsolute;

    private HashMap<String, File> fileMap;

    private String DefaultWebbAppPath(){
        return new File("").getAbsolutePath();
    }

    private int countFiles(String path) {
        File directory=new File(path);
        return directory.list().length;
    }

    public File getFile(String path){
        File file;
        String fileName = webAppPathAbsolute.concat(path);

        file = fileMap.get(fileName);

        if (file == null ) {
            file = new File(fileName);
            fileMap.put(fileName, file);
        }

        return file;
    }


    WebServerFile(String newWebAppPath){
        webAppPathAbsolute = DefaultWebbAppPath().concat("\\src\\main\\").concat(newWebAppPath);
        int countFiles = countFiles(webAppPathAbsolute);
        int initSize = (int) Math.ceil(countFiles/0.75);
        fileMap = new HashMap<>(4);
    }

}
