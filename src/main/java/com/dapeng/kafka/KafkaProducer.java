package com.dapeng.kafka;
import com.alibaba.fastjson.JSON;
import com.dapeng.json.DetailBean;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author Administrator
 * @Date 2020/10/22 13:38
 * @Version 1.0
 * description
 */
public class KafkaProducer {
    public static void producerFirst() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node-1:9092");
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 自定义分区
        props.put("partitioner.class", "com.dapeng.kafka.CustomPartitioner");
        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        try {
            ResultSet rs = JDBCUtils.getRS();
            while(rs.next()){
                String key = rs.getString(1);
                DetailBean detailBean = new DetailBean(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                String value = JSON.toJSONString(detailBean);
                System.out.println(key+"++++++++++"+value);
                producer.send(new ProducerRecord<String, String>("test", key,value));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        producer.close();

    }


    public static void producerSecond(ConsumerRecords<String, String> records) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node-1:9092");
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 自定义分区
//        props.put("partitioner.class", "com.dapeng.kafka.CustomPartitioner");
        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        try {

            HashMap<String, List<DetailBean>> stringListHashMap = new HashMap<>();
            for (ConsumerRecord<String, String> record : records){
                String value = record.value();
                DetailBean detailBean = JSON.parseObject(value, DetailBean.class);

                if(!stringListHashMap.containsKey(record.key())){
                    ArrayList<DetailBean> detailBeans = new ArrayList<DetailBean>();
                    detailBeans.add(detailBean);
                    stringListHashMap.put(record.key(),detailBeans);
                }else{
                    List<DetailBean> detailBeans = stringListHashMap.get(record.key());
                    detailBeans.add(detailBean);
                    stringListHashMap.put(record.key(),detailBeans);
                }


            }


            HashMap string = stringListHashMap;
            Set set = string.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                String key = (String) iterator.next();
                ArrayList value = (ArrayList)string.get(key);
                System.out.println(key+"++++++++++"+value);
                producer.send(new ProducerRecord<String, String>("test1", key,value.toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        producer.close();

    }


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node-1:9092");
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 自定义分区
        props.put("partitioner.class", "com.dapeng.kafka.CustomPartitioner");
        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        try {
            HashMap string = JDBCUtils.getString();
            Set set = string.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                String key = (String) iterator.next();
                ArrayList value = (ArrayList)string.get(key);
                System.out.println(key+"++++++++++"+value);
                producer.send(new ProducerRecord<String, String>("test", key,value.toString()));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        producer.close();

    }


}
