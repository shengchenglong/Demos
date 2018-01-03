package com.kafka.demo;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/2 10:29
 */
public class ApplicationConsumer {

    public static void main(String[] args) {

        KafkaConsumer consumerThread = new KafkaConsumer("topic1");
        consumerThread.start();

    }

}
