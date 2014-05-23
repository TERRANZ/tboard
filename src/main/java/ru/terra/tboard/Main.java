package ru.terra.tboard;

import ru.terra.server.ServerBoot;

import java.io.IOException;

/**
 * Date: 27.02.14
 * Time: 15:26
 */
public class Main {

    public static void main(String[] args) throws IOException {
        new ServerBoot().start();
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
