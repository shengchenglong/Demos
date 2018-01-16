package com.scl.demo;

/**
 * 定义服务器端和客户端都用得到的常量
 *
 * @Author: shengchenglong
 * @Date: 2018/1/9 13:20
 */
public interface Constant {

    /**
     * 服务器地址
     */
    String SERVER = "127.0.0.1";

    /**
     * 监听端口
     */
    int PORT = 6789;

    /**
     * 心跳时间
     */
    int HEART_BEAT = 5000;

}
