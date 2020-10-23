package com.dapeng.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

/**
 * @Author Administrator
 * @Date 2020/10/23 9:29
 * @Version 1.0
 * description
 */
public class KafkaDriver {
    public static void main(String[] args) {
        KafkaProducer.producerFirst();
        KafkaConsumer.consumerFirst();
        KafkaConsumer.consumerSecond();

    }
}
