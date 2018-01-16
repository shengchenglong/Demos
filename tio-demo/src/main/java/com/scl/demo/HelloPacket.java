package com.scl.demo;

import org.tio.core.intf.Packet;

import java.nio.charset.Charset;

/**
 * ①：定义Packet（注：有时候服务器和客户端的业务消息包结构不一样，这种情况下，消息包的定义就不要放在公共模块中，而是在服务端和客户端分别定义）
 *
 * @Author: shengchenglong
 * @Date: 2018/1/9 13:16
 */
public class HelloPacket extends Packet {

    /**
     * 消息头的长度
     */
    public static final int HEADER_LENGTH = 4;

    public static final String CHARSET = "utf-8";

    private byte[] body;

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
