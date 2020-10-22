package com.dapeng.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

import static java.lang.Math.abs;

/**
 * @Author Administrator
 * @Date 2020/10/22 17:31
 * @Version 1.0
 * description
 */
public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int i = abs(key.hashCode() % 2);
        System.out.println(i);
        return i;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
