package ru.nsu.ccfit.lopatkin.lab2.client;

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

        String filePath = "C:\\Users\\loopa\\work\\NSU\\NetworkTechnologiesLabs\\lab2\\client\\10GB.bin";
        //String filePath = args[2];

        //byte[] bytes = filePath.getBytes(StandardCharsets.UTF_8);
        //filePath = new String(bytes, StandardCharsets.UTF_8);

        Path path = Paths.get(filePath);

        if (!Files.exists(path)){
            System.out.println("No such file - " + filePath);
            return;
        }

        ClientRoutine clientRoutine = new ClientRoutine(path, host, port);
        System.out.println("Routine starts");
        clientRoutine.startRoutine();
        System.out.println("Routine finished");

    }
}
