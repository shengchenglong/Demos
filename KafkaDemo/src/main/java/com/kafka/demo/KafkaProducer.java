package com.kafka.demo;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class KafkaProducer extends Thread {

    private final kafka.javaapi.producer.Producer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");

    public KafkaProducer(String topic) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "localhost:9092");


        props.put("partitioner.class", "com.kafka.demo.PartitionDemo");

        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
//        int messageNo = 1;
//        while (true) {
//            String messageStr = new String("Message_" + messageNo);
//            System.out.println(sdf.format(new Date()) + "  Send:" + messageStr);
//            producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
//            messageNo++;
//            try {
//                sleep(3000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }

        for (int i = 1; i <= 5; i++) {
            List messageList = new ArrayList<KeyedMessage<String, String>>();

            for (int j = 0; j < 4; j++) {
                messageList.add(new KeyedMessage<String, String>(topic, j + "", "The " + i + " message for key " + j));

                System.out.println("The " + i + " message for key " + j);
            }
            producer.send(messageList);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }




}