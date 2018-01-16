package com.scl.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

/**
 * 阻塞式I/O创建的客户端
 *
 * @Author: shengchenglong
 * @Date: 2018/1/11 11:05
 */
public class Client implements Runnable {

    public Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String s = Calendar.getInstance().toString();
            System.out.println(s);
            out.println("send: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}