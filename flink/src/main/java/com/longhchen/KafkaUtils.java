package com.longhchen;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Create by longhchen on  2021-03-13 22:31
 */
public class KafkaUtils {
    public static void send(String topic, String key, String data) {
        Properties props = new Properties();
//    props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("bootstrap.servers", "hadoop102:2181");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.send(new ProducerRecord<String, String>(topic, key, "--"+data));
        producer.close();
        System.out.println("finish");
    }
}
