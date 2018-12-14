package com.scl.ws.demo;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOError;
import java.io.IOException;

/**
 * 编程式 WebSocket 端点
 *
 * @Author: shengchenglong
 * @Date: 2018/1/19 15:00
 */
public class EchoServerPrograming extends Endpoint {

    /**
     *
     * @param session           每一个到该端点的客户端都由Session接口的唯一实例表示
     * @param endpointConfig
     */
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String s) {
                try {
                    session.getBasicRemote().sendText("I got this " + s + ") so I am sending it back!");
                } catch (IOException e) {
                    System.out.println("oh dear, something went wrong: " + e.getMessage());
                }
            }
        });
    }
}
