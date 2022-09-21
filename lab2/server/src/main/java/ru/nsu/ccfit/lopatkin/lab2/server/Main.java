package ru.nsu.ccfit.lopatkin.lab2.server;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.lopatkin.lab2.server.threadpool.ThreadPooledServer;
import ru.nsu.ccfit.lopatkin.lab2.server.utils.AliveClientTasksChecker;
import ru.nsu.ccfit.lopatkin.lab2.server.utils.ClientTasksContext;

import java.util.Scanner;
import java.util.Timer;

public class Main {


    private static final Logger logger = Logger.getLogger(Main.class);

    private static final String EXIT_STR = "q";
    public static final int PERIOD = 5000;
    public static final int DELAY = 0;
    public static final int THREAD_NUMBER = 10;

    public static void main(String[] args) {
        logger.info("Server starts!");

        int port = 8080;
        //try {
        //    port = Integer.parseInt(args[0]);
        //} catch (Exception e) {
        //    System.out.println("Bad port value!");
        //    return;
        //}

        ClientTasksContext clientTasksContext = new ClientTasksContext();

        Timer timer = new Timer();
        timer.schedule(new AliveClientTasksChecker(clientTasksContext), DELAY, PERIOD);

        Scanner scanner = new Scanner(System.in);

        ThreadPooledServer server = new ThreadPooledServer(clientTasksContext, port, THREAD_NUMBER);
        new Thread(server).start();
        logger.info("ThreadPool starts!");

        while (true) {
            String inputString = scanner.nextLine();
            if (inputString.equals(EXIT_STR)) {
                logger.info("Start stopping Server!");
                server.stop();
                timer.cancel();
                logger.info("Server died peacefully! RIP Server!");
                break;
            }
        }
        logger.info("Server died peacefully! RIP Server!");
    }
}
