package com.scl.ws.demo;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 * 注解式 WebSocket 端点
 *
 * @Author: shengchenglong
 * @Date: 2018/1/19 10:27
 */
@ServerEndpoint("/echo")
public class EchoServer {

    @OnMessage
    public String echo(String ms) {
        return "I got this (" + ms + ")" +
                " so I am sending it back !";
    }

}
