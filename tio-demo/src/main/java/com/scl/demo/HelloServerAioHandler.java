package com.scl.demo;

import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * ②实现org.tio.server.intf.ServerAioHandler
 *
 * @Author: shengchenglong
 * @Date: 2018/1/9 13:25
 */
public class HelloServerAioHandler implements ServerAioHandler {

    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public Packet decode(ByteBuffer byteBuffer, ChannelContext channelContext) throws AioDecodeException {
        int readableLength = byteBuffer.limit() - byteBuffer.position();
        // 收到的数据包组不了业务，返回null
        if (readableLength < HelloPacket.HEADER_LENGTH) {
            return null;
        }

        // 读取消息的长度
        int bodyLength = byteBuffer.getInt();

        // 数据不正确，AioDecodeException
        if (bodyLength < 0) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
        }

        // 计算本次需要的数据长度
        int neededLength = HelloPacket.HEADER_LENGTH + bodyLength;

        // 收到的数据是否足够组包
        int isDataEnough = readableLength - neededLength;
        // 不够消息体长度(剩下的buffe组不了消息体)
        if (isDataEnough < 0) {
            return null;
        }

        // 组包成功
        HelloPacket imPacket = new HelloPacket();
        if (bodyLength > 0) {
            byte[] dst = new byte[bodyLength];
            byteBuffer.get(dst);
            imPacket.setBody(dst);
        }

        return imPacket;
    }

    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        HelloPacket helloPacket = (HelloPacket) packet;
        byte[] body = helloPacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }

        //bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
        int allLen = HelloPacket.HEADER_LENGTH + bodyLen;
        //创建一个新的bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        //设置字节序
        buffer.order(groupContext.getByteOrder());

        //写入消息头----消息头的内容就是消息体的长度
        buffer.putInt(bodyLen);

        //写入消息体
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }

    /**
     * 处理消息
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        HelloPacket helloPacket = (HelloPacket) packet;
        byte[] body = helloPacket.getBody();
        if (body != null) {
            String str = new String(body, HelloPacket.CHARSET);
            System.out.println("channelContextId: " + channelContext.getId()
                    + " ClientNodeTraceFilename: " + channelContext.getClientNodeTraceFilename()
                    + " clientNode: " + channelContext.getClientNode()
                    + " userId: " + channelContext.getUserid()
                    + " msg: " + str);
            System.out.println();
            // 返回消息
            HelloPacket resppacket = new HelloPacket();
            resppacket.setBody(("fromServer: " + str).getBytes(HelloPacket.CHARSET));
            Aio.send(channelContext, resppacket);
        }
    }
}
