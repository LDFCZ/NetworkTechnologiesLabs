package ru.nsu.ccfit.lopatkin.lab2.client;

import ru.nsu.ccfit.lopatkin.lab2.client.exceptions.FileDoesNotExist;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        String host = "";
        //String host = args[0];

        int port = 8080;
        //try {
        //    port = Integer.parseInt(args[1]);
        //} catch (Exception e) {
        //    System.out.println("Bad port value!");
        //    return;
        //}

        String filePath = "";
        //String filePath = args[2];

        Path path = Paths.get(filePath);
        if (!Files.exists(path)){
            System.out.println("No such file - " + filePath);
            return;
        }



        //byte[] bytes = fileName.getBytes(StandardCharsets.UTF_8);

        //String utf8EncodedFileName = new String(bytes, StandardCharsets.UTF_8);
    }
}
