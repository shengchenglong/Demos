package com.scl.demo.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/11 11:09
 */
public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server.fun(new ServerSocket(12345));

        Thread.sleep(300);

        while (true) {
            new Thread(new Client(new Socket("127.0.0.1", 12345))).start();
            Thread.sleep(3000);
        }
    }
}
