package com.kafka.demo;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * key可以被解析为整数则将对应的整数与Partition总数取余，该消息会被发送到该数对应的Partition
 * （每个Parition都会有个序号,序号从0开始）
 *
 * @Author: shengchenglong
 * @Date: 2018/1/2 11:02
 */
public class PartitionDemo implements Partitioner {

    public PartitionDemo(VerifiableProperties props) {
        //注意 ： 构造函数的函数体没有东西，但是不能没有构造函数
    }

    public int partition(Object key, int numPartitions) {
        try {
            System.out.println("return " + Math.abs(Integer.parseInt((String) key) % numPartitions));
            int partitionNum = Integer.parseInt((String) key);
            return Math.abs(Integer.parseInt((String) key) % numPartitions);
        } catch (Exception e) {
            return Math.abs(key.hashCode() % numPartitions);
        }
    }

}
