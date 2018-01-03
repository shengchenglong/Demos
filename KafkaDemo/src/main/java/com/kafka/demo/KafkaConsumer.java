package com.kafka.demo;


import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 发现 第一次启动 cp都关闭后，
 * 再次单单启动 consumer 会继续接受上次启动时没有接受到的消息
 */
public class KafkaConsumer extends Thread {

    private final ConsumerConnector consumer;
    private final String topic;
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");

    public KafkaConsumer(String topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "group1");

        // zk连接超时
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

        //设定每个topic开几个线程
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
//        while (it.hasNext()) {
//            System.out.println(sdf.format(new Date()) + "  receive：" + new String(it.next().message()));
//            try {
//                sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> message = it.next();
            String topic = message.topic();
            int partition = message.partition();
            long offset = message.offset();
            String key = new String(message.key());
            String msg = new String(message.message());
            // 在这里处理消息,这里仅简单的输出
            // 如果消息消费失败，可以将已上信息打印到日志中，活着发送到报警短信和邮件中，以便后续处理
            System.out.println( " thread : " + Thread.currentThread().getName()
                    + ", topic : " + topic + ", partition : " + partition + ", offset : " + offset + " , key : "
                    + key + " , mess : " + msg);
        }

    }
}