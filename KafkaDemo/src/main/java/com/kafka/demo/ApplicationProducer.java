package com.kafka.demo;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/2 10:28
 */
public class ApplicationProducer {

    public static void main(String[] args) {

        KafkaProducer producerThread = new KafkaProducer("topic1");
        producerThread.start();

    }
}
