package com.scl.demo.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端源码
 *
 * @Author: shengchenglong
 * @Date: 2018/1/11 11:05
 */
public class Server {

    public static void fun(ServerSocket server) throws IOException {
        //使用默认值
        doFun(server);
    }

    //这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步就行了
    public synchronized static void doFun(ServerSocket server) throws IOException {
        try {
            System.out.println("服务器已启动，端口号：" + server.getLocalPort());
            //如果没有客户端接入，将阻塞在accept操作上。
            while (true) {
                Socket socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}